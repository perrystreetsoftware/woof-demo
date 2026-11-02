# Roles never carry feature data; they are enums whose entries resolve theme tokens.

`do-not-use-roles-for-feature-data` · layer: designsystem · disclosure: progressive

## Why

A role is a predefined style the caller picks from a closed set (ButtonRole.primary, IconColorRole.recent). Icons and text are feature data; a role that carries them (IconButtonRole.favorite) ties the design system to one feature.

## How to fix

Keep only theme-backed styling in the role and pass feature data such as icons and content descriptions to the component as parameters.

## Bad

```swift
// Atoms/Button/Roles/IconButtonRole.swift
enum IconButtonRole {
    case favorite
    var icon: ImageAsset { Asset.Icons.starOutline }
    var contentDescription: String { L10n.Accessibility.addFavorite }
}

struct AtomIconButton: View {
    init(role: IconButtonRole, onTap: @escaping () -> Void)
}
```

## Good

```swift
// Atoms/Icon/Roles/IconColorRole.swift
enum IconColorRole {
    case recent
    func color(from theme: ThemeImplementing) -> Color { theme.colors.recent }
}

struct AtomIconButton: View {
    init(icon: ImageAsset, contentDescription: String, colorRole: IconColorRole, onTap: @escaping () -> Void)
}
```

Source: [DoNotUseRolesForFeatureData.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/DesignSystem/DoNotUseRolesForFeatureData.swift)

