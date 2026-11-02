# Molecules, organisms and templates build on atoms, never on the raw SwiftUI components the atoms wrap.

`no-raw-swift-ui-components` · layer: designsystem · disclosure: progressive

## Why

Each atom owns one element's tokens, states and accessibility. A molecule or organism that draws Text, an image, a divider or a progress indicator itself re-implements that atom and bypasses its tokens, so the two drift apart.

## How to fix

Replace the raw component with the atom that wraps it (AtomText, AtomIcon, AtomPainterImage, AtomTextField, AtomHorizontalDivider, AtomCircularProgressIndicator). If no atom covers it, add one. Layout (VStack, HStack, ZStack, Spacer), Button and system presentation such as alerts stay as they are.

## Bad

```swift
struct MolTitleSubtitle: View {
    var body: some View { VStack { Text(title); Text(subtitle) } }
}
```

## Good

```swift
struct MolTitleSubtitle: View {
    var body: some View {
        VStack {
            AtomText(text: title, textFontRole: .subheadP1)
            AtomText(text: subtitle, textFontRole: .bodyP2)
        }
    }
}
```

Source: [NoRawSwiftUIComponents.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/DesignSystem/NoRawSwiftUIComponents.swift)

