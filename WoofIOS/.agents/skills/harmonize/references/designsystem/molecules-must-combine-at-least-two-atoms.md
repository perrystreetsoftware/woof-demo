# Molecules combine at least two atoms.

`molecules-must-combine-at-least-two-atoms` · layer: designsystem · disclosure: progressive

## Why

A molecule exists to compose atoms. One atom in a wrapper adds nothing the atom did not already do.

## How to fix

Combine a second atom, or delete the molecule and call the atom directly. The same atom repeated counts.

## Bad

```swift
public struct MolTitle: View {
    public var body: some View {
        AtomText(text: title, textFontRole: .displayH4, colorRole: .onSurface)
    }
}
```

## Good

```swift
public struct MolTitleSubtitle: View {
    public var body: some View {
        AtomText(text: title, textFontRole: .displayH4, colorRole: .onSurface)
        AtomText(text: subtitle, textFontRole: .subheadP2, colorRole: .onSurfaceVariant)
    }
}
```

Source: [MoleculesMustCombineAtLeastTwoAtoms.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/DesignSystem/MoleculesMustCombineAtLeastTwoAtoms.swift)

