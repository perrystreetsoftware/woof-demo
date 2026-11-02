# Atoms are built from SwiftUI primitives only; they never call other atoms.

`atoms-do-not-compose-other-atoms` · layer: designsystem · disclosure: progressive

## Why

The moment two atoms combine, the result is a molecule. Keeping atoms leaf-level keeps the hierarchy honest and the dependency graph flat.

## How to fix

Promote the composition to a Mol* component in Atomic/Molecules.

## Bad

```swift
struct AtomLabeledIcon: View { var body: some View { HStack { AtomIcon(...); AtomText(...) } } }
```

## Good

```swift
struct MolLabeledIcon: View { var body: some View { HStack { AtomIcon(...); AtomText(...) } } }
```

Source: [AtomsDoNotComposeOtherAtoms.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/DesignSystem/AtomsDoNotComposeOtherAtoms.swift)

