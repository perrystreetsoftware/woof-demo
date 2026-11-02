# ViewModels must be annotated with @Factory.

`view-models-use-factory-annotation` · layer: viewmodel · disclosure: progressive

## Why

The Swinject codegen scans @Factory and generates the registration, so the
DI graph stays uniform and nobody hand-edits containers when a ViewModel gains a dependency.

## How to fix

Add @Factory to the ViewModel class and rerun scripts/SwinjectCodegen.

## Bad

```swift
final class BrowseViewModel: StateProducingViewModel<BrowseViewModel.State, DogsError>
```

## Good

```swift
@Factory
final class BrowseViewModel: StateProducingViewModel<BrowseViewModel.State, DogsError>
```

Source: [ViewModelsUseFactoryAnnotation.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/ViewModel/ViewModelsUseFactoryAnnotation.swift)

