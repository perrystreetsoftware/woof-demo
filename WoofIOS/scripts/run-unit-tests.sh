#!/usr/bin/env bash
set -euo pipefail

cd "$(dirname "$0")/.."

DESTINATION="${DESTINATION:-platform=iOS Simulator,name=iPhone 17 Pro Max}"
DERIVED_DATA="${DERIVED_DATA:-$PWD/.build/DerivedData}"

xcodebuild test \
  -workspace Woof.xcworkspace \
  -scheme Woof \
  -destination "$DESTINATION" \
  -derivedDataPath "$DERIVED_DATA" \
  -skipPackagePluginValidation -skipMacroValidation \
  | grep -E "error:|Test Case.*failed|Executed .* tests|\*\* TEST"
