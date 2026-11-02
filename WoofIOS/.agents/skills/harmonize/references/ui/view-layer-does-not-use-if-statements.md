# Views and screen-layer extensions in presentation packages do not use if statements or optional map.

`view-layer-does-not-use-if-statements` · layer: ui · disclosure: progressive

## Why

Branching in the view hides state decisions from the ViewModel and its tests. Enum state plus
exhaustive switch, or an early viewGuard, keeps every UI branch modelled and tested.

## How to fix

Model the decision in the ViewModel state and switch on it, or use viewGuard(value) { ... } for nullability checks.

## Bad

```swift
if let dialog = state.dialog { OrgAlertDialog(...) }
```

## Good

```swift
viewGuard(state.dialog) { dialog in OrgAlertDialog(...) }
```

Source: [ViewLayerDoesNotUseIfStatements.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/UI/ViewLayerDoesNotUseIfStatements.swift)

