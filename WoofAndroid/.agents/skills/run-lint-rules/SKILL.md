---
name: run-lint-rules
description: Run WoofAndroid's Konsist lint rules and fix what they report, so the code follows Atomic Design and the app's architecture. Use before writing code in an area of WoofAndroid, once the code is written, and again after every fix until the rules pass.
---

# Run the lint rules

The rules live in `konsist/src/test/kotlin/com/perrystreet/woof/konsist/`, one folder per part of the app. Before writing code in an area, read its rules: they say how that part is built.

## 1. Run them

From `WoofAndroid/`, in the foreground, and read the whole output:

```bash
./gradlew runKonsistTests --continue -q
```

## 2. Fix what fails

Every failing rule prints RULE / WHY / HOW TO FIX / BAD / GOOD. Change the code the way HOW TO FIX says. Each rule encodes an architecture decision, so the code moves and the rules stay as they are.

## 3. Repeat

Run them again after every fix. The task is done when every rule passes.
