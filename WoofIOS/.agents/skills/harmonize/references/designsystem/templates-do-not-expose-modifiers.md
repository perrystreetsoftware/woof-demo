# Templates expose slots, never layout values.

`templates-do-not-expose-modifiers` · layer: designsystem · disclosure: progressive

## Why

A template owns the page layout. If a screen could pass a padding or a size, paddings and sizes would again be decided outside the design system.

## How to fix

Build layout parameters into the template and expose @ViewBuilder slots for content.

## Bad

```swift
public struct TemplateGrid<Content: View>: View { public init(padding: CGFloat, @ViewBuilder content: () -> Content) }
```

## Good

```swift
public struct TemplateGrid<TopBar: View, Content: View>: View { public init(@ViewBuilder topBar: () -> TopBar, @ViewBuilder content: () -> Content) }
```

Source: [TemplatesDoNotExposeModifiers.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/DesignSystem/TemplatesDoNotExposeModifiers.swift)

