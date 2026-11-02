# Repositories depend on *DataSourceImplementing protocols and mappers, never on concrete data sources or other repositories.

`repositories-depend-on-data-source-interfaces` · layer: repositories · disclosure: progressive

## Why

Programming against the protocol is what lets tests swap the data source for a fake without
mocks, and keeps repositories from reaching into each other's state.

## How to fix

Inject the *DataSourceImplementing protocol. Compose repositories in a UseCase instead of injecting one into another.

## Bad

```swift
final class DogsRepository { private let dataSource: DogsLocalDataSource; private let moderation: ModerationRepository }
```

## Good

```swift
final class DogsRepository { private let dataSource: DogsDataSourceImplementing; private let pageMapper: DogsPageDTOToDomainMapper }
```

Source: [RepositoriesDependOnDataSourceInterfaces.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/Repositories/RepositoriesDependOnDataSourceInterfaces.swift)

