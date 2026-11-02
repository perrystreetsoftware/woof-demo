# Views never hardcode point values.

`ui-components-do-not-use-hardcoded-values-point` · layer: designsystem · disclosure: progressive

## Why

Spacing, sizing, and radii are tokens tuned in one place. A literal point value bypasses that and drifts between components.

## How to fix

Use PaddingRoles.*, SpacingRoles.*, SizingRoles.*, or theme.radius.* instead of the literal.

## Bad

```swift
.padding(16)
```

## Good

```swift
.padding(PaddingRoles.Element.regular.rawValue)
```

Source: [UIComponentsDoNotUseHardcodedValues.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/DesignSystem/UIComponentsDoNotUseHardcodedValues.swift)

