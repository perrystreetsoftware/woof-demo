import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class RolesLiveInRolesFolder: QuickSpec {
    override class func spec() {
        Given("A Role enum in an atomic component") {
            let roles = WoofHarmonize.atomicDesignPackage.enums().withSuffix("Role").withoutFolder("_Tokens")

            Then("It lives in a Roles folder next to its component") {
                roles.assertTrue(message: message) { $0.filePathString.contains("/Roles/") }
            }

            Then("Its entries resolve to theme tokens or resources") {
                roles.assertTrue(message: message) { role in
                    role.description.contains("theme") || role.description.contains("Asset.") || role.description.contains("L10n.")
                }
            }
        }
    }

    private static let message = LintRuleMessage(
        rule: "Component roles are enums under a Roles folder, and each entry resolves to a theme token or resource.",
        why: """
            Roles are the vocabulary a component offers (TextFontRole.bodyP1, ButtonRole.primary). Keeping them as
            enums next to their component limits the set of tokens a component can use and makes the API discoverable.
            """,
        howToFix: "Move the role into <Component>/Roles/ and make every entry return a theme.* token.",
        badExample: "// Atoms/Text/TextColorRole.swift\nstruct TextColorRole { let color: Color }",
        goodExample: "// Atoms/Text/Roles/TextColorRole.swift\nenum TextColorRole { case onSurface\n    func color(from theme: ThemeImplementing) -> Color { theme.colors.onSurface } }"
    )
}
