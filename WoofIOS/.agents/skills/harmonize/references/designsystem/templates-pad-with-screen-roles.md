# Templates pad with PaddingRoles.Screen, never PaddingRoles.Element.

`templates-pad-with-screen-roles` · layer: designsystem · disclosure: progressive

## Why

Padding applied by a Template is always the gutter between page content and the screen,
so it belongs on the Screen scale. Element padding describes space inside a component,
and a Template has no components of its own — it only places the slots it is given.

## How to fix

Use the Screen step that matches the gutter you want — extraCompact for a dense grid,
compact under a bar, regular for page content, expanded for an overlay drop. Gaps
between items stay on SpacingRoles.

## Bad

```swift
.padding(.horizontal, PaddingRoles.Element.compact.rawValue)
```

## Good

```swift
.padding(.horizontal, PaddingRoles.Screen.extraCompact.rawValue)
```

Source: [TemplatesPadWithScreenRoles.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/DesignSystem/TemplatesPadWithScreenRoles.swift)

