---
name: implement-figma-screen-ios
description: Build a WoofIOS feature from a Figma frame — map the design to existing design-system components through its tokens, add new components at the right atomic layer, implement every layer from data to screen, and iterate on the Harmonize lint rules until they pass. Use whenever the user shares a figma.com link for an iOS screen, tab or feature, or asks to implement a design in WoofIOS.
argument-hint: <figma frame url> [what the feature does]
hooks:
  Stop:
    - hooks:
        - type: command
          command: "$CLAUDE_PROJECT_DIR/.claude/skills/implement-figma-screen-ios/scripts/require-check.sh"
          timeout: 30
---

# Implement a Figma screen on iOS

Input: $ARGUMENTS — a Figma frame link and, optionally, one line on what the feature does.
Scope: `WoofIOS`. Reference feature: `SwiftPackages/Presentation/Favorites`.

## 1. Read the design

With the Figma MCP, fetch the frame's design context (`get_design_context`, with `get_metadata` first if it's large), its variable bindings (`get_variable_defs`) and a screenshot (`get_screenshot`). Read any designer note next to it — it carries the intent the pixels don't.

## 2. Map every element through its tokens, before writing code

Figma variables and text styles carry the same names as the tokens in `_Tokens/` and the roles next to each component, so an element's tokens identify the component that draws it.

Take inventory of `SwiftPackages/DesignSystem/Sources/DesignSystem/Atomic/` first: the folders are the layers, and each component's initializer and tokens are in its code. Then, for each element, collect its tokens (fills, text style, padding, gap, radius, size) and the variants or states it shows, find the component that uses the same set, and confirm against the screenshot. Show the result as a table in your reply before writing any code — Figma element → tokens and states → existing component, or **new** — so every part of the design is either reused or knowingly added. Each component keeps its own sizing; pass it only what the design sets.

## 3. Add new components at the right layer

| What the element is | Layer |
|---|---|
| Page layout with slots for content | `Template*` |
| Section built from molecules, atoms or organisms | `Org*` |
| Group of two or more atoms | `Mol*` |
| One element on SwiftUI primitives | `Atom*` |

Name a new component by its shape and its parameters by the data they hold, since every feature can use it. A new visual variation of an existing component is a new case in its `Roles/` enum; a component's own status is a `State/` enum. The Harmonize rules in `SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/` define each layer and explain why — read the ones for the layer you add to.

## 4. Build every layer

Data source, repository and use case follow the reference feature's `Data` and `Domain` packages. The presentation package, `SwiftPackages/Presentation/<Feature>/Sources/Presentation<Feature>/`:

| Layer | Where | Shape |
|---|---|---|
| View model | `ViewModel/` | `@Factory`, `StateDerivingViewModel` or `StateProducingViewModel`, an `Equatable` `State` enum in an extension, present-tense `on<Action>` taking the tapped UI model |
| UI model + mapper | `UIModel/`, `Mapper/` | `<Name>UIModel` keeping an internal `domain`; `@Factory` mapper with one `callAsFunction` |
| Screen | `UI/<Feature>Screen.swift` | state + closures in, a `Template*` at the root, `#Preview`s wrapped in `ThemedScreenPreview` |
| Adapter | `UI/<Feature>Adapter.swift` | `@StateObject` view model, calls the screen, `.errorAdapter` for errors, navigates with `@Environment(\.navigator)`, calls `onViewAppear()` on appear |
| Resource mapping | `UI/Extensions/` | `switch` over UI state → `L10n`, `Asset`, roles |
| View-model test | `Tests/Presentation<Feature>Tests/` | `<Name>ViewModelTest` as a `QuickSpec` with Given / When / Then: real use cases and repositories over the fakes from `DataSourceFakes` |

Derived flags live in the view model's `State`; extensions only map state to resources. Register the new package everywhere the reference feature's package appears: the `Package.swift` files that depend on it, `Woof.xcworkspace`, `Woof.xcodeproj`, the Woof scheme's test targets, `scripts/SwinjectCodegen` and `Woof/DI/Container+Extensions.swift`. Regenerate the DI registrations with `swift run --package-path scripts/SwinjectCodegen` from `WoofIOS`.

## 5. Check until it passes

```bash
.claude/skills/implement-figma-screen-ios/scripts/check.sh
```

Run it in the foreground and read its whole output. It builds the app, runs the unit tests on the simulator and then the Harmonize rules.

Compile errors print with file and line. Every failing rule prints its description, rationale, fix hint and bad and good examples: change the code the way the fix hint says. Each rule encodes an architecture decision, so the code moves and the rules stay as they are.

Run it again after every fix. The feature is done when the script passes.

## 6. Report

- The mapping table: what was reused, what is new and at which layer.
- Each check run: what failed, and how the code changed.
