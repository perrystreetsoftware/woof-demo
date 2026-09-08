import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class ScreensUseDesignSystemComponents: QuickSpec {
    override class func spec() {
        Given("A file in a feature UI folder") {
            let files = WoofHarmonize.featurePackages.sources().inFolder("UI")

            Then("It does not use raw SwiftUI components, layout modifiers, or design tokens") {
                files.assertFalse(message: message) { file in
                    file.source.containsMatch(of: rawComponentPattern)
                        || file.source.containsMatch(of: layoutModifierPattern)
                        || file.source.containsMatch(of: tokenPattern)
                }
            }
        }
    }

    private static let rawComponentPattern = try! NSRegularExpression(
        pattern: #"\b(Text|Button|Image|VStack|HStack|ZStack|ScrollView|LazyVGrid|LazyVStack|LazyHStack|List|Spacer|Rectangle|RoundedRectangle|Circle|Color)\("#
    )
    private static let layoutModifierPattern = try! NSRegularExpression(pattern: #"\.(padding|frame|background|foregroundStyle|font|overlay|cornerRadius)\("#)
    private static let tokenPattern = try! NSRegularExpression(pattern: #"\b(ColorPrimitives|SpacingPrimitives|SizingPrimitives|SpacingRoles|SizingRoles|ColorRoles|TypographyRoles)\b|\btheme\."#)

    private static let message = LintRuleMessage(
        rule: "Feature UI only composes Template*, Org*, Mol*, and Atom* components and never touches layout modifiers or design tokens.",
        why: """
            If a screen reaches for Text, VStack, .padding, or theme.colors, styling decisions leak out of
            the design system and drift between features. The design system is the only place tokens are resolved.
            """,
        howToFix: "Use the matching atomic component, or add a new one (or a new role) to the design system.",
        badExample: "Text(dog.name).foregroundStyle(theme.colors.onSurface).padding(8)",
        goodExample: "AtomText(text: dog.name, textFontRole: .subheadP2)"
    )
}
