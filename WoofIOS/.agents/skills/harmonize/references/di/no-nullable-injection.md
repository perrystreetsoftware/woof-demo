# Swinject-managed classes never inject nullable dependencies.

`no-nullable-injection` · layer: di · disclosure: progressive

## Why

A nullable dependency is a missing registration in disguise. Either the dependency exists in the graph or the design is wrong.

## How to fix

Make the dependency non-optional and provide it in the DI graph, or split the class.

## Bad

```swift
@Single
final class DogsRepository { private let dataSource: DogsDataSourceImplementing? }
```

## Good

```swift
@Single
final class DogsRepository { private let dataSource: DogsDataSourceImplementing }
```

Source: [NoNullableInjection.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/DI/NoNullableInjection.swift)

