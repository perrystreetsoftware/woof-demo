# The domain layer must not know about DTOs or data sources.

`use-cases-do-not-import-dt-os` · layer: usecase · disclosure: progressive

## Why

DTOs mirror a transport format. If the domain depended on them, every server change would ripple through business rules.

## How to fix

Map the DTO to a domain model in the repository and consume the domain model.

## Bad

```swift
func callAsFunction() -> AnyPublisher<DogsPageDTO, DogsError>
```

## Good

```swift
func callAsFunction() -> AnyPublisher<DogsPage, DogsError>
```

Source: [UseCasesDoNotImportDTOs.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/UseCase/UseCasesDoNotImportDTOs.swift)

