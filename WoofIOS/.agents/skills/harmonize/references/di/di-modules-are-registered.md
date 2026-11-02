# Every generated injection function is called in Container+Extensions.

`di-modules-are-registered` · layer: di · disclosure: progressive

## Why

An unregistered package compiles and passes its own view-model tests, which register the package directly, then crashes on a missing registration the first time the screen opens.

## How to fix

Add .inject<Name>Generated() to injectEverythingForProduction() in Woof/DI/Container+Extensions.swift and the package product to the Woof target.

## Bad

```swift
// Presentation/Favorites/Sources/PresentationFavorites/DI/Container+PresentationFavorites+Generated.swift
func injectPresentationFavoritesGenerated() -> Container { ... }
// …and nothing in Container+Extensions
```

## Good

```swift
func injectEverythingForProduction() -> Container {
    self
        .injectPresentationFavoritesGenerated()
}
```

Source: [DIModulesAreRegistered.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/DI/DIModulesAreRegistered.swift)

