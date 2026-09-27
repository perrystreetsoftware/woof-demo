#!/usr/bin/env bash
# Stop hook for /implement-figma-screen-ios: the agent finishes once check.sh has passed on its latest changes.
set -euo pipefail
cd "$CLAUDE_PROJECT_DIR"
SCRIPTS=.claude/skills/implement-figma-screen-ios/scripts
OUT=WoofIOS/.build/check
mkdir -p "$OUT"

SESSION=$(python3 -c 'import json, sys; print(json.load(sys.stdin)["session_id"])')
[ "$(cat "$OUT/passed" 2>/dev/null)" = "$("$SCRIPTS/fingerprint.sh")" ] && exit 0

BLOCKS=$(( $(cat "$OUT/blocks-$SESSION" 2>/dev/null || echo 0) + 1 ))
echo "$BLOCKS" > "$OUT/blocks-$SESSION"
[ "$BLOCKS" -le 10 ] || exit 0

cat >&2 <<MSG
Your latest changes haven't passed the check yet. Run $SCRIPTS/check.sh in the foreground and fix what it reports. The feature is done when the check passes on your latest changes.
MSG
exit 2
