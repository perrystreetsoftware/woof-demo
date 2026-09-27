---
name: implement-figma-screen
description: Build a WoofAndroid feature from a Figma frame — map the design to existing design-system components through its tokens, add new components at the right atomic layer, implement every layer from data to screen, and iterate on the Konsist lint rules until they pass. Use whenever the user shares a figma.com link for an Android screen, tab or feature, or asks to implement a design in Woof.
argument-hint: <figma frame url> [what the feature does]
hooks:
  Stop:
    - hooks:
        - type: command
          command: "$CLAUDE_PROJECT_DIR/.claude/skills/implement-figma-screen/scripts/require-check.sh"
          timeout: 30
---

# Implement a Figma screen

Input: $ARGUMENTS — a Figma frame link and, optionally, one line on what the feature does.
Scope: `WoofAndroid`. Reference feature: `presentation/favorites`.

## 1. Read the design

With the Figma MCP, fetch the frame's design context (`get_design_context`, with `get_metadata` first if it's large), its variable bindings (`get_variable_defs`) and a screenshot (`get_screenshot`). Read any designer note next to it — it carries the intent the pixels don't.

## 2. Map every element through its tokens, before writing code

Figma variables and text styles carry the same names as the tokens in `_tokens/` and the roles next to each component, so an element's tokens identify the component that draws it.

Take inventory of `design-system/.../atomic/` first: the folders are the layers, and each component's signature and tokens are in its code. Then, for each element, collect its tokens (fills, text style, padding, gap, radius, size) and the variants or states it shows, find the component that uses the same set, and confirm against the screenshot. Show the result as a table in your reply before writing any code — Figma element → tokens and states → existing component, or **new** — so every part of the design is either reused or knowingly added. Each component keeps its own sizing; pass it only what the design sets.

## 3. Add new components at the right layer

| What the element is | Layer |
|---|---|
| Page layout with slots for content | `Template*` |
| Section built from molecules, atoms or organisms | `Org*` |
| Group of two or more atoms | `Mol*` |
| One element on Compose primitives | `Atom*` |

Name a new component by its shape and its parameters by the data they hold, since every feature can use it. A new visual variation of an existing component is a new entry in its `roles/` enum; a component's own status is a `state/` enum. The Konsist rules in `konsist/` define each layer and explain why — read the ones for the layer you add to.

## 4. Build every layer

Data source, repository and use case follow the reference feature's data and domain modules. The presentation layer:

| Layer | Where | Shape |
|---|---|---|
| View model | `presentation/<feature>/viewmodel/` | `@KoinViewModel`, `StateDerivingViewModel` or `StateProducingViewModel`, inner `State`, present-tense `on<Action>` taking the tapped UI model |
| UI model + mapper | `presentation/<feature>/uimodel/`, `mapper/` | `<Name>UIModel` keeping `internal val domain`; `@Factory` mapper with one `invoke` |
| Screen | `presentation/<feature>/ui/<Feature>Screen.kt` | state + lambdas in, a `Template*` at the root, `@PreviewDevices` previews |
| Adapter | `presentation/<feature>/ui/<Feature>Adapter.kt` | view model in, subscribes to state, calls the screen, `ErrorAdapter` for errors, navigates with `LocalNavigator` |
| Resource mapping | `presentation/<feature>/ui/extensions/` | `when` over UI state → strings, drawables, roles |
| View-model test | `presentation/<feature>/src/test/` | `<Name>ViewModelTest` on `ViewModelBehaviorSpec`: real use cases and repositories over the fake data sources from `testFixtures` |

Derived flags live in the view model's `State`; extensions only map state to resources. Wire DI, Gradle and navigation the way the reference feature does.

## 5. Check until it passes and matches the design

```bash
.claude/skills/implement-figma-screen/scripts/check.sh <label> [<label>…]
```

Pass the labels a user taps, in order, to reach the new screen from the app's start. Run it in the foreground and read its whole output. It builds the app and runs the unit tests and Konsist rules; once those pass, it installs the app on the emulator, taps through the labels and saves a screenshot.

- Compile errors print with file and line. Every failing rule prints RULE / WHY / HOW TO FIX / BAD / GOOD: change the code the way HOW TO FIX says. Each rule encodes an architecture decision, so the code moves and the rules stay as they are.
- A missing label or a crash means the screen isn't reachable or doesn't run: wire it the way the reference feature is wired, or fix the crash.
- When it passes, open the Figma screenshot and the emulator screenshot, and for each row describe what each one actually shows (every element present or missing, its size, colour and state) before judging. Fix every difference.

Run it again after every fix. The feature is done when the script passes and the screenshot matches the Figma screenshot.

## 6. Report

- The mapping table: what was reused, what is new and at which layer.
- Each check run: what failed or differed from the design, and how the code changed.
- The path of the final screenshot, next to the Figma one.
