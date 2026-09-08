import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class MappersLiveInMapperPackage: QuickSpec {
    override class func spec() {
        Given("A DTOToDomain mapper") {
            let mappers = WoofHarmonize.productionCode.classes().withSuffix("DTOToDomainMapper")

            Then("It lives in a repositories Mapper folder") {
                mappers.assertTrue(message: message) { $0.filePathString.contains("/Repositories/") && $0.filePathString.contains("/Mapper/") }
            }
        }

        Given("A DomainToUIModel mapper") {
            let mappers = WoofHarmonize.productionCode.classes().withSuffix("UIModelMapper")

            Then("It lives in a feature Mapper folder") {
                mappers.assertTrue(message: message) { $0.filePathString.contains("/Presentation/") && $0.filePathString.contains("/Mapper/") }
            }
        }
    }

    private static let message = LintRuleMessage(
        rule: "DTOToDomain mappers live next to their repository; DomainToUIModel mappers live in Presentation/<Feature>/Mapper.",
        why: "The mapper's location tells you which boundary it crosses. Keeping them with the layer that owns the conversion avoids circular dependencies.",
        howToFix: "Move the mapper to the Mapper folder of the repository or feature that uses it.",
        badExample: "// Domain/UseCase/Sources/UseCase/Dogs/DogDTOToDomainMapper.swift",
        goodExample: "// Data/Repositories/Sources/Repositories/Dogs/Mapper/DogDTOToDomainMapper.swift"
    )
}
