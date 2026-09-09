import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class UIComponentsDoNotUseHardcodedValues: QuickSpec {
    override class func spec() {
        Given("A view outside the primitives") {
            let views = WoofHarmonize.views.withoutFolder("_Primitives")

            Then("It does not hardcode point values") {
                views.assertFalse(message: pointMessage) { $0.description.containsMatch(of: hardcodedPointPattern) }
            }

            Then("It does not hardcode colors") {
                views.assertFalse(message: colorMessage) { $0.description.containsMatch(of: hardcodedColorPattern) }
            }
        }
    }

    private static let hardcodedPointPattern = try! NSRegularExpression(
        pattern: #"\b(padding|spacing|cornerRadius|radius|width|height|minHeight|maxHeight|minWidth|maxWidth|offset|x|y)\s*:\s*-?[1-9]\d*(\.\d+)?\b|\.padding\(\s*[1-9]\d*|\.frame\(\s*[1-9]\d*"#
    )
    private static let hardcodedColorPattern = try! NSRegularExpression(pattern: #"Color\((hex|red|white|hue):"#)

    private static let pointMessage = LintRuleMessage(
        rule: "Views never hardcode point values.",
        why: "Spacing, sizing, and radii are tokens tuned in one place. A literal point value bypasses that and drifts between components.",
        howToFix: "Use PaddingRoles.*, SpacingRoles.*, SizingRoles.*, or theme.radius.* instead of the literal.",
        badExample: ".padding(16)",
        goodExample: ".padding(PaddingRoles.Element.regular.rawValue)"
    )

    private static let colorMessage = LintRuleMessage(
        rule: "Views never hardcode colors.",
        why: "Colors come from the theme so light and dark variants stay in sync. A literal color ignores the active theme.",
        howToFix: "Add a semantic color to Colors and read it through theme.colors or a *ColorRole.",
        badExample: "Text(text).foregroundStyle(Color(hex: 0xFF7A29))",
        goodExample: "AtomText(text: text, textFontRole: .bodyP1, colorRole: .primary)"
    )
}
