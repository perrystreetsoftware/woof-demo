# Only *Adapter views inject ViewModels.

`only-adapters-inject-view-models` · layer: ui · disclosure: progressive

## Why

Adapters bind ViewModels to Screens. Screens stay pure functions of state so they can be
previewed and reasoned about without dependency injection.

## How to fix

Inject the ViewModel in an Adapter and pass state and callbacks down to the Screen.

## Bad

```swift
struct BrowseScreen: View { @StateObject private var viewModel: BrowseViewModel }
```

## Good

```swift
struct BrowseAdapter: View { @StateObject private var viewModel: BrowseViewModel
    var body: some View { BrowseScreen(state: viewModel.state) } }
```

Source: [OnlyAdaptersInjectViewModels.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/UI/OnlyAdaptersInjectViewModels.swift)

