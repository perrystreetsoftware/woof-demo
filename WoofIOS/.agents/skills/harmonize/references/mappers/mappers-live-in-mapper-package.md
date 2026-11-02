# DTOToDomain mappers live next to their repository; DomainToUIModel mappers live in Presentation/<Feature>/Mapper.

`mappers-live-in-mapper-package` · layer: mappers · disclosure: progressive

## Why

The mapper's location tells you which boundary it crosses. Keeping them with the layer that owns the conversion avoids circular dependencies.

## How to fix

Move the mapper to the Mapper folder of the repository or feature that uses it.

## Bad

```swift
// Domain/UseCase/Sources/UseCase/Dogs/DogDTOToDomainMapper.swift
```

## Good

```swift
// Data/Repositories/Sources/Repositories/Dogs/Mapper/DogDTOToDomainMapper.swift
```

Source: [MappersLiveInMapperPackage.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/Mappers/MappersLiveInMapperPackage.swift)

