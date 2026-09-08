import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class RepositoriesUseSingle: QuickSpec {
    override class func spec() {
        Given("A Repository") {
            let repositories = WoofHarmonize.repositories

            Then("It is annotated with @Single") {
                repositories.assertTrue(message: message) { $0.hasAttribute(named: "@Single") }
            }
        }
    }

    private static let message = LintRuleMessage(
        rule: "Repositories must be annotated with @Single.",
        why: "Repositories hold the in-memory state every feature observes. A second instance would mean a second, out-of-sync source of truth.",
        howToFix: "Add @Single to the Repository class.",
        badExample: "@Factory\nfinal class DogsRepository",
        goodExample: "@Single\nfinal class DogsRepository"
    )
}
