# The design system is feature-agnostic: names and the resources it references describe shape and data, never a feature.

`design-system-is-feature-agnostic` · layer: designsystem · disclosure: progressive

## Why

Every feature can use a design-system component, so it is named by what it looks like, its parameters by the data they hold, and its roles by the style they pick. A feature word in the design system, or a feature's string or icon inside a component, ties the component to one screen, and the next feature builds a twin instead of reusing it. Feature words are the feature package names and the domain model names.

## How to fix

Rename the component, its folder, role case or parameter after its structure or what it carries. Pass a feature's text and icons in as parameters; the feature's own words and resources stay in its presentation package.

## Bad

```swift
struct OrgFavoriteCard: View { init(dogName: String, photo: AsyncImageState, onFavoriteTap: @escaping () -> Void) }
```

## Good

```swift
struct OrgPhotoCard: View { init(title: String, imageState: AsyncImageState, contentDescription: String, onTap: @escaping () -> Void) }
```

Source: [DesignSystemIsFeatureAgnostic.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/DesignSystem/DesignSystemIsFeatureAgnostic.swift)

