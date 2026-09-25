import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class DoNotUseRolesForFeatureData: QuickSpec {
    override class func spec() {
        Given("A Role enum in an atomic component") {
            let roles = WoofHarmonize.atomicDesignPackage.enums().withSuffix("Role").withoutFolder("_Tokens")

            Then("Its entries resolve theme tokens") {
                roles.assertTrue(rule: rule) { $0.description.contains("theme") }
            }

            Then("It does not carry feature data such as images or strings") {
                roles.assertFalse(rule: rule) { role in
                    role.description.contains("Asset.") || role.description.contains("L10n.")
                }
            }
        }

        Given("A Roles folder in an atomic component") {
            let files = WoofHarmonize.atomicDesignPackage.sources().inFolder("Roles").withoutFolder("_Tokens")

            Then("It does not import Resources") {
                files.assertFalse(rule: rule) { file in
                    file.imports().contains { $0.name == "Resources" }
                }
            }
        }
    }

    private static let rule = Rule(
        description: "Roles never carry feature data; they are enums whose entries resolve theme tokens.",
        rationale: "A role is a predefined style the caller picks from a closed set (ButtonRole.primary, IconColorRole.recent). Icons and text are feature data; a role that carries them (IconButtonRole.favorite) ties the design system to one feature.",
        fixHint: "Keep only theme-backed styling in the role and pass feature data such as icons and content descriptions to the component as parameters.",
        badExample: """
        // Atoms/Button/Roles/IconButtonRole.swift
        enum IconButtonRole {
            case favorite
            var icon: ImageAsset { Asset.Icons.starOutline }
            var contentDescription: String { L10n.Accessibility.addFavorite }
        }

        struct AtomIconButton: View {
            init(role: IconButtonRole, onTap: @escaping () -> Void)
        }
        """,
        goodExample: """
        // Atoms/Icon/Roles/IconColorRole.swift
        enum IconColorRole {
            case recent
            func color(from theme: ThemeImplementing) -> Color { theme.colors.recent }
        }

        struct AtomIconButton: View {
            init(icon: ImageAsset, contentDescription: String, colorRole: IconColorRole, onTap: @escaping () -> Void)
        }
        """
    )
}
