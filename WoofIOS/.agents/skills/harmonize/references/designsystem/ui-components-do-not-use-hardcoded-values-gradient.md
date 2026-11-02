# Views never build static gradients inline.

`ui-components-do-not-use-hardcoded-values-gradient` · layer: designsystem · disclosure: progressive

## Why

A gradient is a design decision: which colors, and where the ramp starts. Built inline it drifts between components, and its stop positions become literals no token can tune. Animated gradients driven by TimelineView are part of their component's motion and stay private to it.

## How to fix

Add the gradient to GradientRoles and read it through theme.gradients.

## Bad

```swift
LinearGradient(colors: [Color.clear, theme.colors.scrimDim], startPoint: .top, endPoint: .bottom)
```

## Good

```swift
theme.gradients.scrimVertical
```

Source: [UIComponentsDoNotUseHardcodedValues.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/DesignSystem/UIComponentsDoNotUseHardcodedValues.swift)

