# ViewModels must not import repositories or data sources.

`view-models-do-not-depend-on-repositories` · layer: viewmodel · disclosure: progressive

## Why

The flow is View -> ViewModel -> UseCase -> Repository -> DataSource. Skipping the use case
layer spreads business rules across ViewModels and makes them untestable in isolation.

## How to fix

Wrap the repository call in a single-purpose UseCase and inject that instead.

## Bad

```swift
final class BrowseViewModel { private let dogsRepository: DogsRepository }
```

## Good

```swift
final class BrowseViewModel { private let getDogsFeedUseCase: GetDogsFeedUseCase }
```

Source: [ViewModelsDoNotDependOnRepositories.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/ViewModel/ViewModelsDoNotDependOnRepositories.swift)

