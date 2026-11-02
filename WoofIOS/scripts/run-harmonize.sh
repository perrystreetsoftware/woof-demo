#!/usr/bin/env bash
#
# Runs the Harmonize lint rules of WoofIOS and prints only the failures.
# Harmonize writes the violations to JSON files. The script reads those files
# and not the test log, so the output stays short.
#
# Usage, from any folder:
#   bash scripts/run-harmonize.sh                      # all rules
#   bash scripts/run-harmonize.sh RuleName [Rule2 ...] # one or more rules (test class names)

set -o pipefail
cd "$(dirname "$0")/.."

OUT_DIR=$(mktemp -d "${TMPDIR:-/tmp}/harmonize-out.XXXXXX")
trap 'rm -rf "$OUT_DIR"' EXIT
LOG="$OUT_DIR/test.log"
VIOLATIONS_DIR="$OUT_DIR/violations/"
RULES_DIR="SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests"
SKILL_REFERENCES=".agents/skills/harmonize/references"

if ! command -v python3 > /dev/null; then
    echo "ERROR: python3 is required to read the Harmonize JSON report."
    exit 1
fi

print_violations() {
    python3 -I - "$VIOLATIONS_DIR" "$RULES_DIR" "$SKILL_REFERENCES" \
        "${GITHUB_WORKSPACE:-}" "${GITHUB_ACTIONS:-}" <<'PY'
import glob
import json
import os
import sys

violations_dir, rules_dir, references_dir, workspace, in_actions = sys.argv[1:6]
entries = []
for path in sorted(glob.glob(os.path.join(violations_dir, "*.json"))):
    with open(path) as handle:
        entries.extend(json.load(handle))
if not entries:
    sys.exit(3)

# Map a rule id to the test class name: the rule file name without extension.
classes = {}
for root, _, files in os.walk(rules_dir):
    for name in files:
        if name.endswith(".swift"):
            classes[name[:-6].replace("-", "").lower()] = name[:-6]


def class_name(entry):
    rule_id = entry.get("ruleId")
    if rule_id:
        return classes.get(rule_id.replace("-", "").lower())
    base = os.path.basename(entry["file"])
    return base[:-6] if base.endswith(".swift") else None


def relative(path):
    prefixes = [os.getcwd().rstrip("/") + "/"]
    if workspace:
        prefixes.insert(0, workspace.rstrip("/") + "/")
    for prefix in prefixes:
        if path.startswith(prefix):
            return path[len(prefix):]
    return path


def escape(text):
    return text.replace("%", "%25").replace("\r", "%0D").replace("\n", "%0A")


def indent(text, prefix):
    return "\n".join(prefix + line for line in text.splitlines())


groups = {}
order = []
for entry in entries:
    key = entry.get("ruleId") or "assertion:" + entry["file"]
    if key not in groups:
        groups[key] = []
        order.append(key)
    groups[key].append(entry)

print("FAILURES:")
print()
for key in order:
    group = groups[key]
    first = group[0]
    rule_id = first.get("ruleId")
    name = class_name(first)

    if not rule_id:
        print("Rule-level failure in " + (name or relative(first["file"])))
        for entry in group:
            print("  at " + relative(entry["file"]) + ":" + str(entry["line"]))
            print(indent(entry["message"], "  "))
        print()
        continue

    print("Rule: " + (name or rule_id) + " (" + rule_id + ")")
    if first.get("title"):
        print(indent("RULE: " + first["title"], "  "))
    print(indent("WHY: " + first["message"], "  "))
    if first.get("howToFix"):
        print(indent("HOW TO FIX: " + first["howToFix"], "  "))
    pages = sorted(glob.glob(os.path.join(references_dir, "*", rule_id + ".md")))
    if pages:
        print("  Details: " + pages[0])
    print("  Violations:")
    for entry in group:
        location = relative(entry["file"]) + ":" + str(entry["line"])
        print("  - " + location + (" " + entry["name"] if entry.get("name") else ""))
    print()

if in_actions:
    for entry in entries:
        summary = entry.get("title") or entry["message"]
        if entry.get("howToFix"):
            summary = summary + " " + entry["howToFix"]
        print("::error file=" + escape(relative(entry["file"])) + ",line=" + str(entry["line"])
              + "::" + escape(summary))
PY
}

echo "Running Harmonize lint rules..."
if [ $# -gt 0 ]; then
    echo "Rules: $*"
fi
echo "---"

PACKAGE="SwiftPackages/HarmonizeRules"

swift build --package-path "$PACKAGE" --build-tests > "$LOG" 2>&1
EXIT_CODE=$?
if [ $EXIT_CODE -ne 0 ]; then
    echo "BUILD FAILURE: the tests did not compile."
    echo ""
    grep -E "error:" "$LOG" | head -50
    exit $EXIT_CODE
fi

BIN_PATH=$(swift build --package-path "$PACKAGE" --show-bin-path 2>/dev/null)
BUNDLE=$(find "$BIN_PATH" -maxdepth 1 -name "*.xctest" | head -1)
if [ -z "$BUNDLE" ]; then
    echo "ERROR: no test bundle found in $BIN_PATH"
    exit 1
fi

: > "$LOG"
EXIT_CODE=0
if [ "$(uname)" = "Linux" ]; then
    CMD=("$BUNDLE")
    if [ $# -gt 0 ]; then
        FILTER_PATTERN=$(printf "%s|" "$@")
        CMD+=(--filter "${FILTER_PATTERN%|}")
    fi
    HARMONIZE_VIOLATIONS_FILE="$VIOLATIONS_DIR" "${CMD[@]}" >> "$LOG" 2>&1 || EXIT_CODE=$?
elif [ $# -eq 0 ]; then
    HARMONIZE_VIOLATIONS_FILE="$VIOLATIONS_DIR" xcrun xctest "$BUNDLE" >> "$LOG" 2>&1 || EXIT_CODE=$?
else
    for rule in "$@"; do
        HARMONIZE_VIOLATIONS_FILE="$VIOLATIONS_DIR" xcrun xctest -XCTest "HarmonizeRulesTests.$rule" "$BUNDLE" >> "$LOG" 2>&1 || EXIT_CODE=$?
    done
fi

if ! grep -qE "Test Suite .* passed|Test Suite .* failed|Executed .* test" "$LOG"; then
    echo "BUILD FAILURE: the tests did not run."
    echo ""
    grep -E "error:" "$LOG" | grep -v "^$" | head -50
    exit "${EXIT_CODE:-1}"
fi

summary() {
    grep -A1 -E "Test Suite '(All|Selected) tests' (passed|failed)" "$LOG" \
    | awk '/Executed/ { t += $2; f += $5 } END { printf "Executed %d tests, with %d failures\n", t, f }'
}

if [ "$EXIT_CODE" -eq 0 ]; then
    summary
    echo ""
    echo "HARMONIZE LINT RULES PASSED"
    exit 0
fi

if print_violations; then
    echo "---"
    summary
    exit "$EXIT_CODE"
fi

echo "TEST FAILURE: the tests failed, but Harmonize reported no violation."
echo "Errors from the test log:"
echo ""
grep -E "error:" "$LOG" | head -50
exit "$EXIT_CODE"
