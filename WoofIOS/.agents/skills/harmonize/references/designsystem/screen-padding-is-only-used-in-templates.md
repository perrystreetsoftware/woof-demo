# Screen padding roles may only be applied by Templates.

`screen-padding-is-only-used-in-templates` · layer: designsystem · disclosure: progressive

## Why

PaddingRoles.Screen describes the gutter between page content and the screen edge.
Templates are the one layer that owns page level layout, so if an Atom, Molecule or
Organism also applies it the gutter is added twice and drifts between screens.

## How to fix

Let the Template apply the screen padding around its content, and use
PaddingRoles.Element or SpacingRoles inside the component.

## Bad

```swift
public struct OrgFooList: View {
    public var body: some View {
        VStack { FooContent() }
            .padding(.horizontal, PaddingRoles.Screen.regular.rawValue)
    }
}
```

## Good

```swift
public struct TemplateScrollableContent<Content: View>: View {
    public var body: some View {
        ScrollView { content() }
            .padding(.horizontal, PaddingRoles.Screen.regular.rawValue)
    }
}
```

Source: [ScreenPaddingIsOnlyUsedInTemplates.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/DesignSystem/ScreenPaddingIsOnlyUsedInTemplates.swift)

