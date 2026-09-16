import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class RepositoriesUseSingle: QuickSpec {
    override class func spec() {
        Given("A Repository") {
            let repositories = WoofHarmonize.repositories

            Then("It is annotated with @Single") {
                repositories.assertTrue(rule: rule) { $0.hasAttribute(named: "@Single") }
            }
        }
    }

    private static let rule = Rule(
        description: "Repositories must be annotated with @Single.",
        rationale: "Repositories hold the in-memory state every feature observes. A second instance would mean a second, out-of-sync source of truth.",
        fixHint: "Add @Single to the Repository class.",
        badExample: "@Factory\nfinal class DogsRepository",
        goodExample: "@Single\nfinal class DogsRepository"
    )
}
