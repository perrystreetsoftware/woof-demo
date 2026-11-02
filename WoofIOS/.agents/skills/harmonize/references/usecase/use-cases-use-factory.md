# UseCases must be annotated with @Factory.

`use-cases-use-factory` · layer: usecase · disclosure: progressive

## Why

UseCases are stateless, so a new instance per injection is free and rules out accidental shared state.

## How to fix

Add @Factory to the UseCase class.

## Bad

```swift
@Single
final class SendWoofUseCase
```

## Good

```swift
@Factory
final class SendWoofUseCase
```

Source: [UseCasesUseFactory.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/UseCase/UseCasesUseFactory.swift)

