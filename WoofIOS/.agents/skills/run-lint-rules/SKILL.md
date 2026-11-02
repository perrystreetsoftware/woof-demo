---
name: run-lint-rules
description: Run WoofIOS's Harmonize lint rules and fix what they report, so the code follows Atomic Design and the app's architecture. Use after you write code in WoofIOS, and again after every fix until the rules pass.
---

# Run the lint rules

The rules live in `SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/`, one folder per layer of the app. Before you write code in a layer, use the `harmonize` skill. It lists the rules of each layer, with the rationale, the fix, and examples.

## 1. Run them

From `WoofIOS/`, in the foreground. Wait until the script exits:

```bash
bash scripts/run-harmonize.sh                     # all rules
bash scripts/run-harmonize.sh ViewModelsDoNotImportSwiftUI   # one rule, by test class name
```

## 2. Read the result

- **All rules pass:** the script prints the test summary and `HARMONIZE LINT RULES PASSED`.
- **Rules fail:** the script prints one block per failing rule:
  ```
  Rule: <TestClassName> (<rule-id>)
    RULE: <title>
    WHY: <rationale>
    HOW TO FIX: <fix>
    Details: .agents/skills/harmonize/references/<layer>/<rule-id>.md
    Test: <the failed test name; it can include additional details>
    Violations:
    - <file>:<line> <declaration>
  ```
- **No test ran:** the script prints `ERROR: no test ran.` and fails. Check the rule names. A rule name is the test class name.
- **Build failure:** the script prints the build errors. Fix them first.

## 3. Fix what fails

Read the `WHY` and `HOW TO FIX` lines. Open the `Details` page when you need the bad and good examples. Change the production code the way the fix says. Each rule encodes an architecture decision.

## 4. Repeat

Run the failed rule alone after each fix. Then run all rules. The task is done when every rule passes.
