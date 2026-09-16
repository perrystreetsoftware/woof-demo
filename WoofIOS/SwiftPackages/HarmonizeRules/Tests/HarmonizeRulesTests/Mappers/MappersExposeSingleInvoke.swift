import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class MappersExposeSingleInvoke: QuickSpec {
    override class func spec() {
        Given("A DTOToDomain or DomainToUIModel mapper") {
            let mappers = WoofHarmonize.mappers

            Then("It is a @Factory class with a single callAsFunction") {
                mappers.assertTrue(rule: rule) { mapper in
                    let publicFunctions = mapper.functions.withoutModifier(.private)
                    return mapper.hasAttribute(named: "@Factory")
                        && publicFunctions.count == 1
                        && publicFunctions[0].name == "callAsFunction"
                }
            }
        }
    }

    private static let rule = Rule(
        description: "Mappers are @Factory classes exposing exactly one callAsFunction().",
        rationale: "A mapper converts one type into another and nothing else. Injecting it as a class (not a static helper) keeps nested mappers composable.",
        fixHint: "Annotate with @Factory, name the conversion function callAsFunction, and split extra conversions into their own mappers.",
        badExample: "enum DogMapper { static func toDomain(_ dto: DogDTO) -> Dog; static func toUIModel(_ dog: Dog) -> DogCellUIModel }",
        goodExample: "@Factory\nfinal class DogDTOToDomainMapper { func callAsFunction(_ dto: DogDTO) -> Dog }"
    )
}
