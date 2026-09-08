#!/usr/bin/env bash
set -euo pipefail

cd "$(dirname "$0")/.."

PACKAGES=(
  "Presentation/Grid:Grid"
  "Presentation/Profile:Profile"
  "Presentation/Favorites:Favorites"
  "Presentation/Account:Account"
  "Presentation/Home:Home"
)

DESTINATION="${DESTINATION:-platform=iOS Simulator,name=iPhone 17 Pro Max}"
DERIVED_DATA="${DERIVED_DATA:-$PWD/.build/DerivedData}"
STATUS=0

for entry in "${PACKAGES[@]}"; do
  package="${entry%%:*}"
  scheme="${entry##*:}"
  echo "▶ $scheme"
  (
    cd "SwiftPackages/$package"
    xcodebuild test \
      -scheme "$scheme" \
      -destination "$DESTINATION" \
      -derivedDataPath "$DERIVED_DATA" \
      -skipPackagePluginValidation -skipMacroValidation \
      | grep -E "error:|Test Case.*failed|Executed .* tests|\*\* TEST"
  ) || STATUS=1
done

exit $STATUS
