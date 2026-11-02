# Mappers are @Factory classes exposing exactly one callAsFunction().

`mappers-expose-single-invoke` · layer: mappers · disclosure: progressive

## Why

A mapper converts one type into another and nothing else. Injecting it as a class (not a static helper) keeps nested mappers composable.

## How to fix

Annotate with @Factory, name the conversion function callAsFunction, and split extra conversions into their own mappers.

## Bad

```swift
enum DogMapper { static func toDomain(_ dto: DogDTO) -> Dog; static func toUIModel(_ dog: Dog) -> DogCellUIModel }
```

## Good

```swift
@Factory
final class DogDTOToDomainMapper { func callAsFunction(_ dto: DogDTO) -> Dog }
```

Source: [MappersExposeSingleInvoke.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/Mappers/MappersExposeSingleInvoke.swift)

