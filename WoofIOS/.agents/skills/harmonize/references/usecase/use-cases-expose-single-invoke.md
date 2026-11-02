# UseCases expose exactly one callAsFunction() and no public state.

`use-cases-expose-single-invoke` · layer: usecase · disclosure: progressive

## Why

One class, one use case. A single callAsFunction keeps the responsibility obvious and lets callers
use the class like a function. Public state would turn it into a service.

## How to fix

Split additional public functions into their own UseCases; move state into a Repository.

## Bad

```swift
final class DogsUseCase { func load() {}; func block() {} }
```

## Good

```swift
final class LoadNextDogsPageUseCase { func callAsFunction() -> AnyPublisher<DogsPage, DogsError> }
```

Source: [UseCasesExposeSingleInvoke.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/UseCase/UseCasesExposeSingleInvoke.swift)

