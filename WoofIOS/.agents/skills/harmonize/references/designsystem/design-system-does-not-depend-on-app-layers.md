# The design system knows nothing about domain models, UI models, DI, or image loaders.

`design-system-does-not-depend-on-app-layers` · layer: designsystem · disclosure: progressive

## Why

Components take plain data (strings, images, roles). That is what makes them reusable across features
and previewable without a running app. Image requests are built by the caller and passed in as an AsyncImageState.

## How to fix

Accept primitive values, roles, or AsyncImageState instead of feature models, and load images in the presentation layer.

## Bad

```swift
public struct OrgPhotoCard: View { let dog: Dog }
```

## Good

```swift
public struct OrgPhotoCard: View { let title: String; let imageState: AsyncImageState; let onTap: () -> Void }
```

Source: [DesignSystemDoesNotDependOnAppLayers.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/DesignSystem/DesignSystemDoesNotDependOnAppLayers.swift)

