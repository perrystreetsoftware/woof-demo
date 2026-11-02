# Repositories must be annotated with @Single.

`repositories-use-single` · layer: repositories · disclosure: progressive

## Why

Repositories hold the in-memory state every feature observes. A second instance would mean a second, out-of-sync source of truth.

## How to fix

Add @Single to the Repository class.

## Bad

```swift
@Factory
final class DogsRepository
```

## Good

```swift
@Single
final class DogsRepository
```

Source: [RepositoriesUseSingle.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/Repositories/RepositoriesUseSingle.swift)

