# ViewModels must not start subscriptions in init.

`view-models-do-not-subscribe-in-initializers` · layer: viewmodel · disclosure: progressive

## Why

init runs while Swinject constructs the ViewModel, before any view appears. Work started there
cannot be tied to the screen lifecycle and makes tests set up state they never asked for.

## How to fix

Override onFirstAppear() for one-time setup or onEveryAppear() for recurring setup; derive state by passing a publisher to StateDerivingViewModel.

## Bad

```swift
init(...) { loadDogs().sink { ... }.store(in: &cancellables) }
```

## Good

```swift
override func onFirstAppear() { loadDogs().sink { ... }.store(in: &cancellables) }
```

Source: [ViewModelsDoNotSubscribeInInitializers.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/ViewModel/ViewModelsDoNotSubscribeInInitializers.swift)

