import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class DTOsUseCodingKeys: QuickSpec {
    override class func spec() {
        Given("A struct in the DTO package") {
            let dtos = WoofHarmonize.dtoPackage.structs()

            Then("Its name ends with DTO") {
                dtos.assertTrue(rule: rule) { $0.name.hasSuffix("DTO") }
            }

            Then("It is Codable and declares explicit CodingKeys") {
                dtos.assertTrue(rule: rule) { dto in
                    dto.inheritanceTypesNames.contains("Codable") && dto.enums.contains { $0.name == "CodingKeys" }
                }
            }
        }
    }

    private static let rule = Rule(
        description: "DTOs end with DTO, conform to Codable, and declare explicit CodingKeys for every property.",
        rationale: "DTOs are the contract with the transport layer. Explicit JSON keys survive renames, and the suffix makes it obvious the type must be mapped before use.",
        fixHint: "Add the DTO suffix, Codable conformance, and a CodingKeys enum.",
        badExample: "struct Dog { let id: Int; let photoUrl: String }",
        goodExample: "struct DogDTO: Codable { let id: Int; let photoUrl: String\n    enum CodingKeys: String, CodingKey { case id; case photoUrl = \"photo_url\" } }"
    )
}
