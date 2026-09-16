import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class UseCasesUseFactory: QuickSpec {
    override class func spec() {
        Given("A UseCase") {
            let useCases = WoofHarmonize.useCases

            Then("It is annotated with @Factory") {
                useCases.assertTrue(rule: rule) { $0.hasAttribute(named: "@Factory") }
            }
        }
    }

    private static let rule = Rule(
        description: "UseCases must be annotated with @Factory.",
        rationale: "UseCases are stateless, so a new instance per injection is free and rules out accidental shared state.",
        fixHint: "Add @Factory to the UseCase class.",
        badExample: "@Single\nfinal class SendWoofUseCase",
        goodExample: "@Factory\nfinal class SendWoofUseCase"
    )
}
