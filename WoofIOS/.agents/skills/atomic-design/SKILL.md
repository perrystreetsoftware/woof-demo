---
name: atomic-design
description: How the WoofIOS design system is built — its atomic layers from primitives to templates, the tokens, roles and states every component draws with, and how to add a new component at the right layer. Use whenever you read, reuse or add code in WoofIOS's DesignSystem package.
---

# Atomic Design in Woof

The design system lives in `SwiftPackages/DesignSystem/Sources/DesignSystem/Atomic/`. Each folder is a layer, and each layer builds only on the layers before it.

## 1. The layers

| Folder | Holds | Prefix |
|---|---|---|
| `_Primitives/` | Raw values: colours, type, spacing, sizing | |
| `_Tokens/` | Primitives with a meaning, read through the theme and the `*Roles` types | |
| `Atoms/` | One element on SwiftUI primitives | `Atom*` |
| `Molecules/` | A group of two or more atoms | `Mol*` |
| `Organisms/` | A section built from molecules, atoms or other organisms | `Org*` |
| `Templates/` | A page layout with slots for content | `Template*` |

## 2. Tokens, roles and states

- Every colour, text style, spacing, size, radius and alpha comes from a token: `theme.colors`, `theme.typography`, `theme.radius`, `theme.alpha` and the other theme fields, read with `@Environment(\.theme)`, or `SpacingRoles`, `PaddingRoles` and `SizingRoles`.
- A component's visual variations are cases of an enum in its `Roles/` folder. Each case resolves its look from tokens.
- A component's own status is an enum or immutable struct in its `State/` folder, and it also resolves its look from tokens.

## 3. Find what exists

Take inventory before adding anything. The folders are the layers, and each component's initializer and the tokens it draws with are in its code. A component that uses the same tokens and shows the same states is the one to reuse.

## 4. Add a component

1. Put it in the lowest layer that fits, with that layer's prefix.
2. Build it from the layers below it and from tokens.
3. Name it by its shape and its parameters by the data they hold, so every feature can use it.
4. Give it the current values to show, and let the screen decide which ones.
5. Templates take view-builder slots; organisms, molecules and atoms take data.
6. A new visual variation of an existing component is a new case in its `Roles/` enum.

The Harmonize rules in `HarmonizeRules/.../DesignSystem/` define each layer; the run-lint-rules skill covers them.
