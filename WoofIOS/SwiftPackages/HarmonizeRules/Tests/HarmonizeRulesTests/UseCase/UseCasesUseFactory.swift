import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class UseCasesUseFactory: QuickSpec {
    override class func spec() {
        Given("A UseCase") {
            let useCases = WoofHarmonize.useCases

            Then("It is annotated with @Factory") {
                useCases.assertTrue(message: message) { $0.hasAttribute(named: "@Factory") }
            }
        }
    }

    private static let message = LintRuleMessage(
        rule: "UseCases must be annotated with @Factory.",
        why: "UseCases are stateless, so a new instance per injection is free and rules out accidental shared state.",
        howToFix: "Add @Factory to the UseCase class.",
        badExample: "@Single\nfinal class SendWoofUseCase",
        goodExample: "@Factory\nfinal class SendWoofUseCase"
    )
}
