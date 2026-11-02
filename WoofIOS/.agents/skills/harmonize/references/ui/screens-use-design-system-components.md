# Feature UI only composes Template*, Org*, Mol*, and Atom* components and never touches layout modifiers or design tokens.

`screens-use-design-system-components` · layer: ui · disclosure: progressive

## Why

If a screen reaches for Text, VStack, .padding, or theme.colors, styling decisions leak out of
the design system and drift between features. The design system is the only place tokens are resolved.

## How to fix

Use the matching atomic component, or add a new one (or a new role) to the design system.

## Bad

```swift
Text(dog.name).foregroundStyle(theme.colors.onSurface).padding(8)
```

## Good

```swift
AtomText(text: dog.name, textFontRole: .subheadP2)
```

Source: [ScreensUseDesignSystemComponents.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/UI/ScreensUseDesignSystemComponents.swift)

