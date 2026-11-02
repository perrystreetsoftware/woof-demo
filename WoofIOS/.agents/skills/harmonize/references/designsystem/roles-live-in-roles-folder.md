# Component roles live in a Roles folder next to their component.

`roles-live-in-roles-folder` · layer: designsystem · disclosure: progressive

## Why

Roles are the vocabulary a component offers (TextFontRole.bodyP1, ButtonRole.primary); keeping them next to the component makes the API discoverable.

## How to fix

Move the role into <Component>/Roles/.

## Bad

```swift
// Atoms/Text/TextColorRole.swift
enum TextColorRole { ... }
```

## Good

```swift
// Atoms/Text/Roles/TextColorRole.swift
enum TextColorRole { ... }
```

Source: [RolesLiveInRolesFolder.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/DesignSystem/RolesLiveInRolesFolder.swift)

