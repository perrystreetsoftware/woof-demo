# ViewModels, UI models, and mappers must not import SwiftUI, design system, or resource types.

`view-models-do-not-import-swift-ui` · layer: viewmodel · disclosure: progressive

## Why

Presentation logic must be testable without a UI framework and reusable across views. Localized strings
and views are view concerns and belong in screen-layer extensions.

## How to fix

Emit plain data in the UI model and map it to strings, icons, or colors in an extension under UI/Extensions.

## Bad

```swift
struct ProfileToastUIModel { let message: LocalizedStringKey }
```

## Good

```swift
enum ProfileToastUIModel { case woofSent(name: String) }
extension ProfileToastUIModel { var text: String { ... } }
```

Source: [ViewModelsDoNotImportSwiftUI.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/ViewModel/ViewModelsDoNotImportSwiftUI.swift)

