# Atomic layers only depend on the layers below them: _Primitives -> _Tokens -> Atoms -> Molecules -> Organisms -> Templates.

`design-system-atomic-dependencies` · layer: designsystem · disclosure: progressive

## Why

Strict layering is what makes the design system composable: an atom can be reused anywhere because it
depends on nothing above it, and a molecule never hides a whole organism inside.

## How to fix

Move shared code down (to atoms or tokens) or composition up (to organisms or templates) so references follow the dependency flow.

## Bad

```swift
// in Atoms/Text/AtomText.swift
MolButton(text: text, onTap: onTap)
```

## Good

```swift
// in Molecules/Button/MolButton.swift
AtomText(text: text, textFontRole: .displayH4)
```

Source: [DesignSystemAtomicDependencies.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/DesignSystem/DesignSystemAtomicDependencies.swift)

