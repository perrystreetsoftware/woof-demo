#!/usr/bin/env bash
set -euo pipefail

cd "$(dirname "$0")/.."

DERIVED_DATA="${DERIVED_DATA:-$PWD/.build/DerivedData}"

(
  cd SwiftPackages/HarmonizeRules
  xcodebuild test \
    -scheme HarmonizeRules \
    -destination "platform=macOS" \
    -derivedDataPath "$DERIVED_DATA" \
    -skipPackagePluginValidation -skipMacroValidation \
    | grep -E "error:|Test Case.*failed|Executed .* tests|\*\* TEST"
)
