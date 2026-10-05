#!/usr/bin/env bash
# Stop hook for /implement-screen on iOS: the agent finishes once check.sh has passed on its latest changes.
set -euo pipefail
cd "$(git rev-parse --show-toplevel)"
SCRIPTS=WoofIOS/.agents/skills/implement-screen/scripts
OUT=WoofIOS/.build/check
mkdir -p "$OUT"

[ "$(cat "$OUT/passed" 2>/dev/null)" = "$("$SCRIPTS/fingerprint.sh")" ] && exit 0

cat >&2 <<MSG
Your latest changes haven't passed the check yet. Run $SCRIPTS/check.sh in the foreground and fix what it reports. The feature is done when the check passes on your latest changes.
MSG
exit 2
