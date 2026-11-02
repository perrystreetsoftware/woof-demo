# Custom view modifiers are private.

`custom-modifiers-are-private` · layer: ui · disclosure: progressive

## Why

A public modifier spreads styling outside the component that owns it, so callers restyle one-off instead of picking a variant.

## How to fix

Make the modifier private to its component. If several components need it, move them into that file as initializers of one component.

## Bad

```swift
public extension View {
    func atomPlaceholderShimmer() -> some View { modifier(AtomPlaceholderShimmer()) }
}
```

## Good

```swift
public struct AtomPlaceholder: View {
    public init(role: TextPlaceholderRole) { ... }
    public init(role: ShapePlaceholderRole) { ... }
}

private extension View {
    func placeholderShimmer(radius: CGFloat) -> some View { modifier(PlaceholderShimmer(radius: radius)) }
}
```

Source: [CustomModifiersArePrivate.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/UI/CustomModifiersArePrivate.swift)

