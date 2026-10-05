---
name: atomic-design
description: How the WoofAndroid design system is built — its atomic layers from primitives to templates, the tokens, roles and states every component draws with, and how to add a new component at the right layer. Use whenever you read, reuse or add code in WoofAndroid's design-system module.
---

# Atomic Design in Woof

The design system lives in `design-system/src/main/kotlin/com/perrystreet/woof/designsystem/atomic/`. Each folder is a layer, and each layer builds only on the layers before it.

## 1. The layers

| Folder | Holds | Prefix |
|---|---|---|
| `_primitives/` | Raw values: colours, type, spacing, sizing | |
| `_tokens/` | Primitives with a meaning, read through `Theme` and the `*Roles` objects | |
| `atoms/` | One element on Compose primitives | `Atom*` |
| `molecules/` | A group of two or more atoms | `Mol*` |
| `organisms/` | A section built from molecules, atoms or other organisms | `Org*` |
| `templates/` | A page layout with slots for content | `Template*` |

## 2. Tokens, roles and states

- Every colour, text style, spacing, size, radius and alpha comes from a token: `Theme.colors`, `Theme.typography`, `Theme.radius`, `Theme.alpha` and the other `Theme` fields, or `SpacingRoles`, `PaddingRoles` and `SizingRoles`.
- A component's visual variations are entries in an enum in its `roles/` folder. Each entry resolves its look from tokens.
- A component's own status is an enum or immutable class in its `state/` folder, and it also resolves its look from tokens.

## 3. Find what exists

Take inventory before adding anything. The folders are the layers, and each component's signature and the tokens it draws with are in its code. A component that uses the same tokens and shows the same states is the one to reuse.

## 4. Add a component

1. Put it in the lowest layer that fits, with that layer's prefix.
2. Build it from the layers below it and from tokens.
3. Name it by its shape and its parameters by the data they hold, so every feature can use it.
4. Give it the current values to show, and let the screen decide which ones.
5. Templates take composable slots; organisms, molecules and atoms take data.
6. A new visual variation of an existing component is a new entry in its `roles/` enum.

The Konsist rules in `konsist/.../designsystem/` define each layer; the run-lint-rules skill covers them.
