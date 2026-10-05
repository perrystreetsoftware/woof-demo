---
name: run-lint-rules
description: Run WoofIOS's Harmonize lint rules and fix what they report, so the code follows Atomic Design and the app's architecture. Use before writing code in an area of WoofIOS, once the code is written, and again after every fix until the rules pass.
---

# Run the lint rules

The rules live in `SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/`, one folder per part of the app. Before writing code in an area, read its rules: they say how that part is built.

## 1. Run them

From `WoofIOS/`, in the foreground, and read the whole output:

```bash
scripts/run-harmonize.sh
```

## 2. Fix what fails

Every failing rule prints an `error:` line with its description, rationale, fix hint and bad and good examples. Change the code the way the fix hint says. Each rule encodes an architecture decision, so the code moves and the rules stay as they are.

## 3. Repeat

Run them again after every fix. The task is done when every rule passes.
