# Every data source is a *DataSourceImplementing protocol with local and fake implementations.

`data-sources-are-backed-by-interfaces` · layer: datasource · disclosure: progressive

## Why

The protocol is the seam between the app and the outside world. Repositories, tests, and a future remote implementation all program against it.

## How to fix

Declare protocol FooDataSourceImplementing, then make FooLocalDataSource and FakeFooDataSource conform to it.

## Bad

```swift
final class DogsLocalDataSource { func getDogs() -> AnyPublisher<DogsPageDTO, DataSourceError> }
```

## Good

```swift
protocol DogsDataSourceImplementing { func getDogs() -> AnyPublisher<DogsPageDTO, DataSourceError> }
final class DogsLocalDataSource: DogsDataSourceImplementing
```

Source: [DataSourcesAreBackedByInterfaces.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/DataSource/DataSourcesAreBackedByInterfaces.swift)

