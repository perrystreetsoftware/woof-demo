import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class StateTypesAreEquatable: QuickSpec {
    override class func spec() {
        Given("A State type or UI model in a feature package") {
            let types = WoofHarmonize.featurePackages.enums().withName("State").map(\.inheritanceTypesNames)
                + WoofHarmonize.featurePackages.structs().withName("State").map(\.inheritanceTypesNames)
                + WoofHarmonize.featurePackages.enums().withSuffix("UIModel").map(\.inheritanceTypesNames)
                + WoofHarmonize.featurePackages.structs().withSuffix("UIModel").map(\.inheritanceTypesNames)

            Then("It is Equatable") {
                let violations = types.filter { !$0.contains("Equatable") && !$0.contains("Hashable") }
                WoofHarmonize.featurePackages.sources().assertTrue(message: message) { _ in violations.isEmpty }
            }
        }
    }

    private static let message = LintRuleMessage(
        rule: "State types and UI models must be Equatable.",
        why: "States mix enums with associated values and structs. Equatable gives every branch the same equality semantics, which keeps test assertions symmetric.",
        howToFix: "Add Equatable (or Hashable) conformance to the type.",
        badExample: "public enum State { case loading }",
        goodExample: "public enum State: Equatable { case loading }"
    )
}
