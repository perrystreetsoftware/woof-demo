# Every ViewModel has a <Name>Test.

`view-models-have-tests` · layer: tests · disclosure: progressive

## Why

The ViewModel test is the single place where a feature's behaviour is specified end to end, from user action down to the fake data source.

## How to fix

Add <Name>Test as a QuickSpec in the feature's test target.

## Bad

```swift
final class ProfileWoofViewModel  // no ProfileWoofViewModelTest
```

## Good

```swift
final class ProfileWoofViewModelTest: QuickSpec
```

Source: [ViewModelsHaveTests.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/Tests/ViewModelsHaveTests.swift)

