# ViewModels must extend StateProducingViewModel, StateDerivingViewModel, or StatelessViewModel.

`view-models-inherit-from-base-view-models` · layer: viewmodel · disclosure: progressive

## Why

The base classes own the cancellables, the error channel, the lifecycle hooks, and the published
state contract. Extending ObservableObject directly loses all of that.

## How to fix

Pick StateProducingViewModel when the ViewModel mutates state, StateDerivingViewModel when state is combined from streams, StatelessViewModel otherwise.

## Bad

```swift
final class BrowseViewModel: ObservableObject
```

## Good

```swift
final class BrowseViewModel: StateProducingViewModel<BrowseViewModel.State, DogsError>
```

Source: [ViewModelsInheritFromBaseViewModels.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/ViewModel/ViewModelsInheritFromBaseViewModels.swift)

