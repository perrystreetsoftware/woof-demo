# DTOs end with DTO, conform to Codable, and declare explicit CodingKeys for every property.

`dt-os-use-coding-keys` · layer: models · disclosure: progressive

## Why

DTOs are the contract with the transport layer. Explicit JSON keys survive renames, and the suffix makes it obvious the type must be mapped before use.

## How to fix

Add the DTO suffix, Codable conformance, and a CodingKeys enum.

## Bad

```swift
struct Dog { let id: Int; let photoUrl: String }
```

## Good

```swift
struct DogDTO: Codable { let id: Int; let photoUrl: String
    enum CodingKeys: String, CodingKey { case id; case photoUrl = "photo_url" } }
```

Source: [DTOsUseCodingKeys.swift](../../../../../SwiftPackages/HarmonizeRules/Tests/HarmonizeRulesTests/Models/DTOsUseCodingKeys.swift)

