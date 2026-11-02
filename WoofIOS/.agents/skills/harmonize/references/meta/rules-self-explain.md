# Every lint rule passes a Rule to its assertion.

`rules-self-explain` · layer: meta · disclosure: progressive

## Why

A failing rule must explain why it exists and how to fix it, otherwise the next person works around it instead of fixing the code.

## How to fix

Replace the raw string with a Rule(title, category, rationale, howToFix, badExample, goodExample).

## Bad

```swift
classes.assertTrue(message: "Use @Factory") { ... }
```

## Good

```swift
classes.assertTrue(rule: rule) { ... }
```

Source: [RulesSelfExplain.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/Meta/RulesSelfExplain.swift)

