# State types and UI models must be Equatable.

`state-types-are-equatable` · layer: models · disclosure: progressive

## Why

States mix enums with associated values and structs. Equatable gives every branch the same equality semantics, which keeps test assertions symmetric.

## How to fix

Add Equatable (or Hashable) conformance to the type.

## Bad

```swift
public enum State { case loading }
```

## Good

```swift
public enum State: Equatable { case loading }
```

Source: [StateTypesAreEquatable.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/Models/StateTypesAreEquatable.swift)

