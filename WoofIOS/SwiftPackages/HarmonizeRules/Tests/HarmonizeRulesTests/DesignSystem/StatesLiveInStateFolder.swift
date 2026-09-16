import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class StatesLiveInStateFolder: QuickSpec {
    override class func spec() {
        Given("A State type in an atomic component") {
            let enums = WoofHarmonize.atomicDesignPackage.enums().withSuffix("State").withoutFolder("_Tokens")
            let structs = WoofHarmonize.atomicDesignPackage.structs().withSuffix("State").withoutFolder("_Tokens")
                .filter { !$0.inheritanceTypesNames.contains("View") }

            Then("It lives in a State folder next to its component") {
                enums.assertTrue(message: message) { $0.filePathString.contains("/State/") }
                structs.assertTrue(message: message) { $0.filePathString.contains("/State/") }
            }
        }
    }

    private static let message = LintRuleMessage(
        rule: "Component states live in a State folder next to their component.",
        why: "A state is part of a component's API (ButtonState, OverflowMenuState); keeping it next to the component makes the API discoverable.",
        howToFix: "Move the state into <Component>/State/.",
        badExample: "// Molecules/Button/ButtonState.swift\nenum ButtonState { ... }",
        goodExample: "// Molecules/Button/State/ButtonState.swift\nenum ButtonState { ... }"
    )
}
