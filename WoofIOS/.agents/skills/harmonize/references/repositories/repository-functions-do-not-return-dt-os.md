# Repositories never expose DTOs.

`repository-functions-do-not-return-dt-os` · layer: repositories · disclosure: progressive

## Why

The repository is the boundary where transport data becomes domain data. Leaking a DTO pushes mapping into every caller.

## How to fix

Map with a DTOToDomainMapper inside the repository and return the domain model.

## Bad

```swift
func getDogs() -> AnyPublisher<DogsPageDTO, DogsError>
```

## Good

```swift
func getDogs() -> AnyPublisher<DogsPage, DogsError> { dataSource.getDogs().map { pageMapper($0) } }
```

Source: [RepositoryFunctionsDoNotReturnDTOs.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/Repositories/RepositoryFunctionsDoNotReturnDTOs.swift)

