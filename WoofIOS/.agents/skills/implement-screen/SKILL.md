---
name: implement-screen
description: Build a WoofIOS feature from a Figma frame — map the design to existing design-system components through its tokens, add what's missing, implement every layer from data to screen and check it builds, passes its tests and follows the lint rules. Use whenever the user shares a figma.com link for an iOS screen, tab or feature, or asks to implement a design in WoofIOS.
argument-hint: <figma frame url> [what the feature does]
hooks:
  Stop:
    - hooks:
        - type: command
          command: "$(git rev-parse --show-toplevel)/WoofIOS/.agents/skills/implement-screen/scripts/require-check.sh"
          timeout: 30
---

# Implement a Figma screen on iOS

Input: $ARGUMENTS — a Figma frame link and, optionally, one line on what the feature does.
Scope: `WoofIOS`. Reference feature: `SwiftPackages/Presentation/Favorites`.

Load the atomic-design and run-lint-rules skills before you start.

## 1. Read the design

With the Figma MCP, fetch the frame's design context (`get_design_context`, with `get_metadata` first if it's large), its variable bindings (`get_variable_defs`) and a screenshot (`get_screenshot`). Read any designer note next to it — it carries the intent the pixels don't.

## 2. Map every Figma element to a component, before writing code

Figma variables and text styles carry the same names as the tokens in `_Tokens/` and the roles next to each component, so an element's tokens identify the component that draws it.

For each element, collect its tokens (fills, text style, padding, gap, radius, size) and the variants or states it shows, find the existing component the way the atomic-design skill describes, and confirm against the screenshot. Show the result as a table in your reply before writing any code — Figma element → tokens and states → existing component, or **new** — so every part of the design is either reused or knowingly added. Each component keeps its own sizing; pass it only what the design sets. Add each **new** one the way the atomic-design skill describes.

## 3. Build every layer

Data source, repository and use case follow the reference feature's `Data` and `Domain` packages. The presentation package, `SwiftPackages/Presentation/<Feature>/Sources/Presentation<Feature>/`:

| Layer | Where | Shape |
|---|---|---|
| View model | `ViewModel/` | `@Factory`, `StateDerivingViewModel` or `StateProducingViewModel`, an `Equatable` `State` enum in an extension, present-tense `on<Action>` taking the tapped UI model |
| UI model + mapper | `UIModel/`, `Mapper/` | `<Name>UIModel` keeping an internal `domain`; `@Factory` mapper with one `callAsFunction` |
| Screen | `UI/<Feature>Screen.swift` | state + closures in, a `Template*` at the root, `#Preview`s wrapped in `ThemedScreenPreview` |
| Adapter | `UI/<Feature>Adapter.swift` | `@StateObject` view model, calls the screen, `.errorAdapter` for errors, navigates with `@Environment(\.navigator)`, calls `onViewAppear()` on appear |
| Resource mapping | `UI/Extensions/` | `switch` over UI state → `L10n`, `Asset`, roles |
| View-model test | `Tests/Presentation<Feature>Tests/` | `<Name>ViewModelTest` as a `QuickSpec` with Given / When / Then: real use cases and repositories over the fakes from `DataSourceFakes` |

Derived flags live in the view model's `State`. Register the new package everywhere the reference feature's package appears: the `Package.swift` files that depend on it, `Woof.xcworkspace`, `Woof.xcodeproj`, the Woof scheme's test targets, `scripts/SwinjectCodegen` and `Woof/DI/Container+Extensions.swift`. Regenerate the DI registrations with `swift run --package-path scripts/SwinjectCodegen` from `WoofIOS`.

## 4. Check until it passes

Once the code is written, run the lint rules with the run-lint-rules skill, and again after every fix until they pass. Then run the full check:

```bash
WoofIOS/.agents/skills/implement-screen/scripts/check.sh
```

Run it in the foreground and read its whole output. It builds the app, runs the unit tests on the simulator and then the Harmonize rules.

Compile errors print with file and line. Fix a failing rule the way the run-lint-rules skill says.

Run it again after every fix. The feature is done when the script passes.

## 5. Report

- The mapping table: what was reused, what is new and at which layer.
- Each check run: what failed, and how the code changed.
