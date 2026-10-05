#!/usr/bin/env bash
# Stop hook for /implement-screen: the agent finishes once check.sh has passed on its latest changes.
set -euo pipefail
cd "$(git rev-parse --show-toplevel)"
SCRIPTS=WoofAndroid/.agents/skills/implement-screen/scripts
OUT=WoofAndroid/build/emulator
mkdir -p "$OUT"

[ "$(cat "$OUT/passed" 2>/dev/null)" = "$("$SCRIPTS/fingerprint.sh")" ] && exit 0

cat >&2 <<EOF
Your latest changes haven't passed the check yet. Run $SCRIPTS/check.sh in the foreground with the labels that reach the new screen, fix what it reports, and compare its screenshot with the Figma screenshot. The feature is done when the check passes on your latest changes and the screenshot matches the design.
EOF
exit 2
