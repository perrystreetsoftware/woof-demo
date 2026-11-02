# Text the user sees comes from string resources, never from literals in presentation code.

`presentation-text-comes-from-resources` · layer: ui · disclosure: progressive

## Why

Resources are where text is translated, pluralised and reviewed in one place. A literal can't be localised, and any value it formats belongs in the mapper or view model, where it is tested.

## How to fix

Add a string to Localizable.strings and read it through L10n. Compute the values the text shows in the mapper or view model and pass them to the resource.

## Bad

```swift
case .age(let ageInYears): return "\(ageInYears) years"
```

## Good

```swift
case .age(let ageInYears): return L10n.Profile.age(ageInYears)
```

Source: [PresentationTextComesFromResources.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/UI/PresentationTextComesFromResources.swift)

