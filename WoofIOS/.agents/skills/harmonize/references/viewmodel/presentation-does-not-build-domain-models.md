# Presentation code receives domain models from use cases; only previews construct them.

`presentation-does-not-build-domain-models` · layer: viewmodel · disclosure: progressive

## Why

Domain models come from a data source through a repository and a use case. A view model that builds its own list skips those layers, so the screen shows data the app never loads, and its tests check the literals instead of the behaviour.

## How to fix

Add the data source, repository and use case the way the reference feature does, inject the use case into the view model and derive the state from it. Keep hand-made models in UI/Preview for previews and in DataSourceFakes for tests.

## Bad

```swift
// FavoritesViewModel
Just([Dog(id: 1, name: "Rufus", photoUrl: "")]).map { ... }
```

## Good

```swift
// FavoritesViewModel
getFavoriteDogsUseCase().map { dogs in ... }
```

Source: [PresentationDoesNotBuildDomainModels.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/ViewModel/PresentationDoesNotBuildDomainModels.swift)

