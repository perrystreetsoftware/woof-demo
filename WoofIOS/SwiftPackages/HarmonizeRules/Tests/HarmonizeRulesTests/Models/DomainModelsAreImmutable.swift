import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class DomainModelsAreImmutable: QuickSpec {
    override class func spec() {
        Given("A stored property of a domain model") {
            let properties = WoofHarmonize.domainModelPackage.structs().flatMap(\.variables).filter(\.isStored)

            Then("It is declared with let") {
                properties.assertTrue(rule: rule) { $0.isConstant }
            }
        }
    }

    private static let rule = Rule(
        description: "Domain models only have let properties.",
        rationale: "Domain models flow through Combine streams shared by several ViewModels. Mutable fields would let one consumer change what another is observing.",
        fixHint: "Use let and build new instances to derive changes.",
        badExample: "struct Dog { var name: String }",
        goodExample: "struct Dog { let name: String }"
    )
}
