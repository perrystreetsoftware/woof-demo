# Every Adapter delegates rendering to a Screen view.

`adapters-call-screens` · layer: ui · disclosure: progressive

## Why

The Adapter/Screen split only works if the Adapter is a thin bridge: observe state, bind callbacks, render the Screen.

## How to fix

Move layout code into a *Screen view and call it from the Adapter.

## Bad

```swift
struct BrowseAdapter: View { var body: some View { TemplateGrid(...) { ... } } }
```

## Good

```swift
struct BrowseAdapter: View { var body: some View { BrowseScreen(state: viewModel.state, onCellTap: ...) } }
```

Source: [AdaptersCallScreens.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/UI/AdaptersCallScreens.swift)

