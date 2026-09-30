#!/usr/bin/env bash
# Builds WoofAndroid, runs the unit tests and Konsist rules, then installs the app on an emulator,
# taps each given label in order to reach the new screen, saves a screenshot and fails on a crash.
# Usage: check.sh <label> [<label>...]
set -euo pipefail

[ $# -gt 0 ] || { echo "Pass the labels to tap, in order, to reach the new screen from the app's start." >&2; exit 2; }

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
    SDK=$(sed -n 's/^sdk.dir=//p' "$ROOT/local.properties")
    AVD=$("$SDK/emulator/emulator" -list-avds | head -1)
    echo "Starting emulator ${AVD}…"
    nohup "$SDK/emulator/emulator" -avd "$AVD" >/dev/null 2>&1 &
    adb wait-for-device
    until [ "$(adb shell getprop sys.boot_completed 2>/dev/null | tr -d '\r')" = "1" ]; do sleep 2; done
fi

(cd "$ROOT" && ./gradlew :app:installDebug -q >/dev/null)
adb logcat -c
adb shell am force-stop "$APP"
adb shell am start -W -n "$APP/.MainActivity" >/dev/null
sleep 2

tap() {
    adb shell uiautomator dump /sdcard/ui.xml >/dev/null
    adb pull /sdcard/ui.xml "$OUT/ui.xml" >/dev/null 2>&1
    local point
    point=$(python3 - "$OUT/ui.xml" "$1" <<'EOF'
import re, sys, xml.etree.ElementTree as ET
label = sys.argv[2]
for node in ET.parse(sys.argv[1]).iter("node"):
    if label in (node.get("text"), node.get("content-desc")):
        x1, y1, x2, y2 = map(int, re.findall(r"\d+", node.get("bounds")))
        print((x1 + x2) // 2, (y1 + y2) // 2)
        break
EOF
)
    [ -n "$point" ] || { echo "No element labelled \"$1\" on screen. Labels on screen:" >&2; grep -oE '(text|content-desc)="[^"]+"' "$OUT/ui.xml" | sort -u >&2; exit 1; }
    adb shell input tap $point
    sleep 2
}

for label in "$@"; do tap "$label"; done

DISPLAY_ID=$(adb shell dumpsys SurfaceFlinger --display-id | head -1 | awk '{print $2}')
adb shell screencap -p -d "$DISPLAY_ID" /sdcard/screen.png
adb pull /sdcard/screen.png "$OUT/screen.png" >/dev/null 2>&1

CRASH=$(adb logcat -d -b crash)
[ -z "$CRASH" ] || { echo "The app crashed:" >&2; echo "$CRASH" >&2; exit 1; }
echo "$CHECKED" > "$OUT/passed"
echo "Reached the screen without a crash. Screenshot: $OUT/screen.png"
