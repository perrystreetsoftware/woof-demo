# An organism that contains every part of an existing organism calls that organism instead of rebuilding it.

`organisms-reuse-existing-organisms` · layer: designsystem · disclosure: progressive

## Why

Two organisms made of the same parts drift apart: a fix or a new size lands in one and not the other, and the design system grows a near-twin for every screen. Calling the existing organism keeps one source for that shape.

## How to fix

Call the existing organism inside the new one and add only what it lacks. Where the new one looks different, such as another size or typography, add a case to the existing organism's Roles enum and pass it. The test name lists which organism rebuilds which.

## Bad

```swift
struct OrgErrorState: View {
    var body: some View {
        VStack {
            AtomAppLogo(...); AtomText(text: title, ...); AtomText(text: message, ...)
            MolButton(text: actionText, onTap: onActionTap)
        }
    }
}
// OrgEmptyState already draws AtomAppLogo + AtomText + AtomText
```

## Good

```swift
struct OrgErrorState: View {
    var body: some View {
        VStack {
            OrgEmptyState(title: title, message: message)
            MolButton(text: actionText, onTap: onActionTap)
        }
    }
}
```

Source: [OrganismsReuseExistingOrganisms.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/DesignSystem/OrganismsReuseExistingOrganisms.swift)

