import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class RolesLiveInRolesFolder: QuickSpec {
    override class func spec() {
        Given("A Role enum in an atomic component") {
            let roles = WoofHarmonize.atomicDesignPackage.enums().withSuffix("Role").withoutFolder("_Tokens")

            Then("It lives in a Roles folder next to its component") {
                roles.assertTrue(rule: rule) { $0.filePathString.contains("/Roles/") }
            }
        }
    }

    private static let rule = Rule(
        title: "Component roles live in a Roles folder next to their component.",
        category: WoofRuleCategory.designSystem,
        rationale: "Roles are the vocabulary a component offers (TextFontRole.bodyP1, ButtonRole.primary); keeping them next to the component makes the API discoverable.",
        howToFix: "Move the role into <Component>/Roles/.",
        badExample: "// Atoms/Text/TextColorRole.swift\nenum TextColorRole { ... }",
        goodExample: "// Atoms/Text/Roles/TextColorRole.swift\nenum TextColorRole { ... }"
    )
}
