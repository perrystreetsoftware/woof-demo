# Views never hardcode colors.

`ui-components-do-not-use-hardcoded-values-color` · layer: designsystem · disclosure: progressive

## Why

Colors come from the theme so light and dark variants stay in sync. A literal color ignores the active theme.

## How to fix

Add a semantic color to Colors and read it through theme.colors or a *ColorRole.

## Bad

```swift
Text(text).foregroundStyle(Color(hex: 0xFF7A29))
```

## Good

```swift
AtomText(text: text, textFontRole: .bodyP1, colorRole: .primary)
```

Source: [UIComponentsDoNotUseHardcodedValues.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/DesignSystem/UIComponentsDoNotUseHardcodedValues.swift)

