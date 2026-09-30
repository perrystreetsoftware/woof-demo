#!/usr/bin/env bash
# Stop hook for /implement-figma-screen on iOS: the agent finishes once check.sh has passed on its latest changes.
set -euo pipefail
cd "$(git rev-parse --show-toplevel)"
SCRIPTS=WoofIOS/.agents/skills/implement-figma-screen/scripts
OUT=WoofIOS/.build/check
mkdir -p "$OUT"

SESSION=$(python3 -c 'import json, sys; print(json.load(sys.stdin)["session_id"])')
[ "$(cat "$OUT/passed" 2>/dev/null)" = "$("$SCRIPTS/fingerprint.sh")" ] && exit 0

BLOCKS=$(( $(cat "$OUT/blocks-$SESSION" 2>/dev/null || echo 0) + 1 ))
echo "$BLOCKS" > "$OUT/blocks-$SESSION"
if [ "$BLOCKS" -gt 10 ]; then
    echo "{\"systemMessage\": \"Stopped after 10 blocked attempts: check.sh never passed on the latest changes.\"}"
    exit 0
fi

cat >&2 <<MSG
Your latest changes haven't passed the check yet. Run $SCRIPTS/check.sh in the foreground and fix what it reports. The feature is done when the check passes on your latest changes.
MSG
exit 2
