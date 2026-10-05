---
name: implement-screen
description: Build a WoofAndroid feature from a Figma frame — map the design to existing design-system components through its tokens, add what's missing, implement every layer from data to screen and check it on the emulator. Use whenever the user shares a figma.com link for an Android screen, tab or feature, or asks to implement a design in Woof.
argument-hint: <figma frame url> [what the feature does]
hooks:
  Stop:
    - hooks:
        - type: command
          command: "$(git rev-parse --show-toplevel)/WoofAndroid/.agents/skills/implement-screen/scripts/require-check.sh"
          timeout: 30
---

# Implement a Figma screen

Input: $ARGUMENTS — a Figma frame link and, optionally, one line on what the feature does.
Scope: `WoofAndroid`. Reference feature: `presentation/favorites`.

Load the atomic-design, run-lint-rules and android-cli skills before you start.

## 1. Read the design

With the Figma MCP, fetch the frame's design context (`get_design_context`, with `get_metadata` first if it's large), its variable bindings (`get_variable_defs`) and a screenshot (`get_screenshot`). Read any designer note next to it — it carries the intent the pixels don't.

## 2. Map every Figma element to a component, before writing code

Figma variables and text styles carry the same names as the tokens in `_tokens/` and the roles next to each component, so an element's tokens identify the component that draws it.

For each element, collect its tokens (fills, text style, padding, gap, radius, size) and the variants or states it shows, find the existing component the way the atomic-design skill describes, and confirm against the screenshot. Show the result as a table in your reply before writing any code — Figma element → tokens and states → existing component, or **new** — so every part of the design is either reused or knowingly added. Each component keeps its own sizing; pass it only what the design sets. Add each **new** one the way the atomic-design skill describes.

## 3. Build every layer

Data source, repository and use case follow the reference feature's data and domain modules. The presentation layer:

| Layer | Where | Shape |
|---|---|---|
| View model | `presentation/<feature>/viewmodel/` | `@KoinViewModel`, `StateDerivingViewModel` or `StateProducingViewModel`, inner `State`, present-tense `on<Action>` taking the tapped UI model |
| UI model + mapper | `presentation/<feature>/uimodel/`, `mapper/` | `<Name>UIModel` keeping `internal val domain`; `@Factory` mapper with one `invoke` |
| Screen | `presentation/<feature>/ui/<Feature>Screen.kt` | state + lambdas in, a `Template*` at the root, `@PreviewDevices` previews |
| Adapter | `presentation/<feature>/ui/<Feature>Adapter.kt` | view model in, subscribes to state, calls the screen, `ErrorAdapter` for errors, navigates with `LocalNavigator` |
| Resource mapping | `presentation/<feature>/ui/extensions/` | `when` over UI state → strings, drawables, roles |
| View-model test | `presentation/<feature>/src/test/` | `<Name>ViewModelTest` on `ViewModelBehaviorSpec`: real use cases and repositories over the fake data sources from `testFixtures` |

Derived flags live in the view model's `State`. Wire DI, Gradle and navigation the way the reference feature does.

## 4. Check until it passes and matches the design

Once the code is written, run the lint rules with the run-lint-rules skill, and again after every fix until they pass. Then run the full check:

```bash
WoofAndroid/.agents/skills/implement-screen/scripts/check.sh <label> [<label>…]
```

Pass the labels a user taps, in order, to reach the new screen from the app's start. Run it in the foreground and read its whole output. It builds the app and runs the unit tests and Konsist rules; once those pass, it installs the app on the emulator, taps through the labels and saves a screenshot.

- Compile errors print with file and line. Fix a failing rule the way the run-lint-rules skill says.
- A missing label or a crash means the screen isn't reachable or doesn't run: wire it the way the reference feature is wired, or fix the crash.
- When it passes, open the Figma screenshot and the emulator screenshot, and for each row describe what each one actually shows (every element present or missing, its size, colour and state) before judging. Fix every difference. To check other states, such as after a tap, drive the app with the android-cli skill.

Run it again after every fix. The feature is done when the script passes and the screenshot matches the Figma screenshot.

## 5. Report

- The mapping table: what was reused, what is new and at which layer.
- Each check run: what failed or differed from the design, and how the code changed.
- The path of the final screenshot, next to the Figma one.
