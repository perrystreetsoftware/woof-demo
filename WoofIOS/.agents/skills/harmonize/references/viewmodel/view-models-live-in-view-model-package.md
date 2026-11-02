# ViewModels live in Presentation/<Feature>/Sources/<Target>/ViewModel.

`view-models-live-in-view-model-package` · layer: viewmodel · disclosure: progressive

## Why

Each feature package has the same four folders (ViewModel, UIModel, Mapper, UI), so anyone can find a class by convention.

## How to fix

Move the class to the ViewModel folder of its feature.

## Bad

```swift
// Presentation/Browse/Sources/PresentationBrowse/UI/BrowseViewModel.swift
```

## Good

```swift
// Presentation/Browse/Sources/PresentationBrowse/ViewModel/BrowseViewModel.swift
```

Source: [ViewModelsLiveInViewModelPackage.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/ViewModel/ViewModelsLiveInViewModelPackage.swift)

