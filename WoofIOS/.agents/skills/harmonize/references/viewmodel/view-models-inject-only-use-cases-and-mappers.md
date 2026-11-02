# ViewModels may only inject UseCases, Mappers, NavigatorImplementing (for state-driven navigation), and @DIArgument values.

`view-models-inject-only-use-cases-and-mappers` · layer: viewmodel · disclosure: progressive

## Why

ViewModels orchestrate use cases and shape their output for the UI. Any other dependency
(repositories, data sources, framework classes) belongs to a lower layer.

## How to fix

Move the work into a UseCase or a Mapper and inject that.

## Bad

```swift
final class ProfileWoofViewModel { private let woofsRepository: WoofsRepository }
```

## Good

```swift
final class ProfileWoofViewModel { @DIArgument private let dog: Dog; private let sendWoofUseCase: SendWoofUseCase }
```

Source: [ViewModelsInjectOnlyUseCasesAndMappers.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/ViewModel/ViewModelsInjectOnlyUseCasesAndMappers.swift)

