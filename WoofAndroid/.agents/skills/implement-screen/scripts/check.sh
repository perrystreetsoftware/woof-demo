#!/usr/bin/env bash
# Builds WoofAndroid, runs the unit tests and Konsist rules, then installs the app on an emulator,
# taps each given label in order to reach the new screen, saves a screenshot and fails on a crash.
# Drives the emulator with the Android CLI (`android`); the android-cli skill explains how to install it.
# Usage: check.sh <label> [<label>...]
set -euo pipefail

[ $# -gt 0 ] || { echo "Pass the labels to tap, in order, to reach the new screen from the app's start." >&2; exit 2; }
command -v android >/dev/null || { echo "Install the Android CLI the way the android-cli skill describes, then run this again." >&2; exit 2; }

ROOT="$(git rev-parse --show-toplevel)/WoofAndroid"
APP=com.perrystreet.woof
OUT="$ROOT/build/emulator"
mkdir -p "$OUT"
CHECKED=$("$(dirname "$0")/fingerprint.sh")

if ! LINT=$(cd "$ROOT" && ./gradlew :app:assembleDebug runUnitTests runKonsistTests --continue -q 2>&1); then
    echo "$LINT"
    echo
    echo "Build, tests or Konsist rules failed. Fix the code and run this again."
    exit 1
fi
echo "Build, unit tests and Konsist rules pass."

if ! adb get-state >/dev/null 2>&1; then
    AVD=$(android emulator list | sort | head -1)
    echo "Starting emulator ${AVD}…"
    android emulator start "$AVD" >/dev/null
fi

adb logcat -c
adb shell am force-stop "$APP"
RUN=$(android run --apks="$ROOT/app/build/outputs/apk/debug/app-debug.apk" 2>&1) || { echo "$RUN" >&2; exit 1; }
sleep 2

tap() {
    android layout --output="$OUT/layout.json" >/dev/null
    local point
    point=$(python3 - "$OUT/layout.json" "$1" <<'EOF'
import json, re, sys
label = sys.argv[2]
def walk(nodes):
    for node in nodes:
        yield node
        yield from walk(node.get("children", []))
for window in json.load(open(sys.argv[1])):
    if window.get("type") == "APPLICATION":
        for node in walk(window.get("content", [])):
            if label in (node.get("text"), node.get("content-desc")):
                print(*re.findall(r"\d+", node["center"]))
                sys.exit()
EOF
)
    [ -n "$point" ] || { echo "No element labelled \"$1\" on screen. Labels on screen:" >&2; grep -oE '"(text|content-desc)":"[^"]+"' "$OUT/layout.json" | sort -u >&2; exit 1; }
    adb shell input tap $point
    sleep 2
}

for label in "$@"; do tap "$label"; done

# android screen capture can't pick a display yet, and the resizable emulators have several.
DISPLAY_ID=$(adb shell dumpsys SurfaceFlinger --display-id | head -1 | awk '{print $2}')
adb shell screencap -p -d "$DISPLAY_ID" /sdcard/screen.png
adb pull /sdcard/screen.png "$OUT/screen.png" >/dev/null 2>&1

CRASH=$(adb logcat -d -b crash)
[ -z "$CRASH" ] || { echo "The app crashed:" >&2; echo "$CRASH" >&2; exit 1; }
echo "$CHECKED" > "$OUT/passed"
echo "Reached the screen without a crash. Screenshot: $OUT/screen.png"
