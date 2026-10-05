#!/usr/bin/env bash
# Builds WoofIOS, runs the unit tests and Harmonize rules, and records a passing run for the Stop hook.
# Usage: check.sh
set -euo pipefail

ROOT="$(git rev-parse --show-toplevel)/WoofIOS"
OUT="$ROOT/.build/check"
mkdir -p "$OUT"
CHECKED=$("$(dirname "$0")/fingerprint.sh")
export DESTINATION="${DESTINATION:-platform=iOS Simulator,name=iPhone 18 Pro Max}"

if ! TESTS=$("$ROOT/scripts/run-unit-tests.sh" 2>&1); then
    echo "$TESTS"
    echo
    echo "Build or unit tests failed. Fix the code and run this again."
    exit 1
fi

if ! RULES=$("$ROOT/scripts/run-harmonize.sh" 2>&1) || grep -q "error:" <<<"$RULES"; then
    echo "$RULES"
    echo
    echo "Harmonize rules failed. Fix the code and run this again."
    exit 1
fi

echo "$CHECKED" > "$OUT/passed"
echo "Build, unit tests and Harmonize rules pass."
