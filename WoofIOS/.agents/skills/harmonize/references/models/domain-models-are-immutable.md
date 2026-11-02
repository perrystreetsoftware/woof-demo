# Domain models only have let properties.

`domain-models-are-immutable` · layer: models · disclosure: progressive

## Why

Domain models flow through Combine streams shared by several ViewModels. Mutable fields would let one consumer change what another is observing.

## How to fix

Use let and build new instances to derive changes.

## Bad

```swift
struct Dog { var name: String }
```

## Good

```swift
struct Dog { let name: String }
```

Source: [DomainModelsAreImmutable.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/Models/DomainModelsAreImmutable.swift)

