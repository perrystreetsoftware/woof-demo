# Screen views use a Template* as their root layout.

`screens-use-templates` · layer: ui · disclosure: progressive

## Why

Templates own page structure (top bar, insets, paddings, scrolling). Screens only fill their slots, so every screen shares the same skeleton.

## How to fix

Wrap the screen content in an existing Template, or add a new Template to the design system.

## Bad

```swift
struct BrowseScreen: View { var body: some View { ScrollView { LazyVGrid { ... } } } }
```

## Good

```swift
struct BrowseScreen: View { var body: some View { TemplateGrid(topBar: { OrgNavigationHeaderBranded() }) { ... } } }
```

Source: [ScreensUseTemplates.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/UI/ScreensUseTemplates.swift)

