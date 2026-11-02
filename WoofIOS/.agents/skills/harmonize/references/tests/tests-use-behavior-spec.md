# Tests are QuickSpecs written as Given / When / Then with beforeEach setup and justBeforeEach actions.

`tests-use-behavior-spec` · layer: tests · disclosure: progressive

## Why

Tests target the ViewModel layer only, which exercises use cases and repositories underneath.
The BDD structure reads as a spec, and a fresh Container per beforeEach gives every Then a clean slate.

## How to fix

Subclass QuickSpec, use Given/When/Then/And blocks, set up in beforeEach, act in justBeforeEach, and advance time with TimeAdvancingFactory(container).tick().

## Bad

```swift
final class BrowseViewModelTest: XCTestCase { func testLoads() { sleep(1) } }
```

## Good

```swift
final class BrowseViewModelTest: QuickSpec { override class func spec() { Given("I open the grid") { Then("...") { ... } } } }
```

Source: [TestsUseBehaviorSpec.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/Tests/TestsUseBehaviorSpec.swift)

