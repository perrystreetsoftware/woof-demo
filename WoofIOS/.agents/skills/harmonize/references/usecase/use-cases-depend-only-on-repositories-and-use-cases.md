# UseCases may only depend on Repositories and other UseCases.

`use-cases-depend-only-on-repositories-and-use-cases` · layer: usecase · disclosure: progressive

## Why

Use cases compose data access into business rules. Data sources, mappers, or view types would leak other layers into the domain.

## How to fix

Inject a Repository that wraps the data source, or another UseCase.

## Bad

```swift
final class SendWoofUseCase { private let dataSource: WoofsDataSourceImplementing }
```

## Good

```swift
final class SendWoofUseCase { private let woofsRepository: WoofsRepository }
```

Source: [UseCasesDependOnlyOnRepositoriesAndUseCases.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/UseCase/UseCasesDependOnlyOnRepositoriesAndUseCases.swift)

