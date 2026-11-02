# Tests never use mocking libraries.

`tests-do-not-use-mocks` · layer: tests · disclosure: progressive

## Why

Mocks couple tests to implementation details and let broken collaborations pass. The data source layer
has hand-written fakes configured through *DataSourceFactory builders, and everything above it runs for real.

## How to fix

Configure the fake data source with a factory, e.g. DogsDataSourceFactory(container).withDogs(count: 450).

## Bad

```swift
let useCase = MockGetDogsFeedUseCase()
```

## Good

```swift
DogsDataSourceFactory(container).withDogs(count: 450)
```

Source: [TestsDoNotUseMocks.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/Tests/TestsDoNotUseMocks.swift)

