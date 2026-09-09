import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class TemplatesPadWithScreenRoles: QuickSpec {
    override class func spec() {
        Given("A view in the templates layer") {
            let templates = WoofHarmonize.atomicDesignPackage.structs().views.inFolder("Templates")

            Then("It pads with screen roles, never element roles") {
                templates.assertFalse(message: message) { $0.description.contains(elementPaddingUsage) }
            }
        }
    }

    private static let elementPaddingUsage = "PaddingRoles.Element"

    private static let message = LintRuleMessage(
        rule: "Templates pad with PaddingRoles.Screen, never PaddingRoles.Element.",
        why: """
            Padding applied by a Template is always the gutter between page content and the screen,
            so it belongs on the Screen scale. Element padding describes space inside a component,
            and a Template has no components of its own — it only places the slots it is given.
            """,
        howToFix: """
            Use the Screen step that matches the gutter you want — extraCompact for a dense grid,
            compact under a bar, regular for page content, expanded for an overlay drop. Gaps
            between items stay on SpacingRoles.
            """,
        badExample: ".padding(.horizontal, PaddingRoles.Element.compact.rawValue)",
        goodExample: ".padding(.horizontal, PaddingRoles.Screen.extraCompact.rawValue)"
    )
}
