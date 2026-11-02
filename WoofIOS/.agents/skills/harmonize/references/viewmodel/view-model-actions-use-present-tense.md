# ViewModel actions are named on<Something><Verb>, never in the past tense.

`view-model-actions-use-present-tense` · layer: viewmodel · disclosure: progressive

## Why

Actions describe what the user does right now (onWoofTap), not what already happened.
One naming style keeps adapters and tests predictable.

## How to fix

Rename onButtonTapped to onButtonTap, onTextChanged to onTextChange.

## Bad

```swift
func onWoofTapped()
```

## Good

```swift
func onWoofTap()
```

Source: [ViewModelActionsUsePresentTense.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/ViewModel/ViewModelActionsUsePresentTense.swift)

