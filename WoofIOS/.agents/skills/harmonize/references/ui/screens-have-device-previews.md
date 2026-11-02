# Every Screen has a #Preview wrapped in ThemedScreenPreview with an explicit theme.

`screens-have-device-previews` · layer: ui · disclosure: progressive

## Why

Previews are how a screen is reviewed in every theme without running the app. ThemedScreenPreview renders the screen with the light or dark WoofTheme.

## How to fix

Add a #Preview whose body is ThemedScreenPreview(theme: WoofTheme.light()) { Screen(...) }.

## Bad

```swift
#Preview { BrowseScreen(...) }
```

## Good

```swift
#Preview("Loaded") { ThemedScreenPreview(theme: WoofTheme.light()) { BrowseScreen(...) } }
```

Source: [ScreensHaveDevicePreviews.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/UI/ScreensHaveDevicePreviews.swift)

