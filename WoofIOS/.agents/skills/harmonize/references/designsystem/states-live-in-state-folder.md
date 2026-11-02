# Component states live in a State folder next to their component.

`states-live-in-state-folder` · layer: designsystem · disclosure: progressive

## Why

A state is part of a component's API (ButtonState, OverflowMenuState); keeping it next to the component makes the API discoverable.

## How to fix

Move the state into <Component>/State/.

## Bad

```swift
// Molecules/Button/ButtonState.swift
enum ButtonState { ... }
```

## Good

```swift
// Molecules/Button/State/ButtonState.swift
enum ButtonState { ... }
```

Source: [StatesLiveInStateFolder.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/DesignSystem/StatesLiveInStateFolder.swift)

