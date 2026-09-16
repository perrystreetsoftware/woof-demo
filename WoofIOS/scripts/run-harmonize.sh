#!/usr/bin/env bash
set -euo pipefail

cd "$(dirname "$0")/.."

swift test --package-path SwiftPackages/HarmonizeRules 2>&1 \
  | grep -E "error:|failed|Executed [0-9]+ tests"
