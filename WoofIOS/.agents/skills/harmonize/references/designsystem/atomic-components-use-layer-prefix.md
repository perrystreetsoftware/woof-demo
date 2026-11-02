# Public views are prefixed by their layer: Atom*, Mol*, Org*, Template*.

`atomic-components-use-layer-prefix` · layer: designsystem · disclosure: progressive

## Why

The prefix tells the caller which layer they are composing with, so a Screen can be checked for using only Template/Org/Mol/Atom at a glance.

## How to fix

Rename the view with the prefix of the folder it lives in, or move it to the right layer.

## Bad

```swift
// in Atomic/Molecules/Button/
public struct PrimaryButton: View
```

## Good

```swift
// in Atomic/Molecules/Button/
public struct MolButton: View
```

Source: [AtomicComponentsUseLayerPrefix.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/DesignSystem/AtomicComponentsUseLayerPrefix.swift)

