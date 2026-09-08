import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class DesignSystemAtomicDependencies: QuickSpec {
    override class func spec() {
        Given("A file in the atomic design system") {
            let files = WoofHarmonize.atomicDesignPackage.sources()

            Then("Primitives depend on nothing else in the design system") {
                files.inFolder("_Primitives").assertFalse(message: message) { file in
                    file.references(tokenPattern) || file.references(componentPattern) || file.references(themePattern)
                }
            }

            Then("Tokens depend only on primitives") {
                files.inFolder("_Tokens").assertFalse(message: message) { file in
                    file.references(componentPattern) || file.references(themePattern)
                }
            }

            Then("Atoms depend only on tokens and the theme") {
                files.inFolder("Atoms").assertFalse(message: message) { file in
                    file.references(primitivePattern) || file.references(moleculePattern) || file.references(organismPattern) || file.references(templatePattern)
                }
            }

            Then("Molecules depend only on atoms, tokens, and the theme") {
                files.inFolder("Molecules").assertFalse(message: message) { file in
                    file.references(primitivePattern) || file.references(organismPattern) || file.references(templatePattern) || file.referencesOtherMolecules()
                }
            }

            Then("Organisms depend only on molecules, atoms, tokens, the theme, and other organisms") {
                files.inFolder("Organisms").assertFalse(message: message) { file in
                    file.references(primitivePattern) || file.references(templatePattern)
                }
            }

            Then("Templates depend only on organisms, molecules, atoms, tokens, and the theme") {
                files.inFolder("Templates").assertFalse(message: message) { file in
                    file.references(primitivePattern)
                }
            }
        }
    }

    private static let primitivePattern = try! NSRegularExpression(pattern: #"\b(Color|Alpha|Spacing|Sizing|Typography)Primitives\b"#)
    private static let tokenPattern = try! NSRegularExpression(pattern: #"\b(Colors|ColorRoles|AlphaRoles|PaddingRoles|SpacingRoles|SizingRoles|Typography|TypographyRoles|MotionRoles|AspectRatioRoles)\b"#)
    private static let themePattern = try! NSRegularExpression(pattern: #"\b(ThemeImplementing|WoofTheme)\b"#)
    private static let componentPattern = try! NSRegularExpression(pattern: #"\b(Atom|Mol|Org|Template)[A-Z]\w*\b"#)
    private static let moleculePattern = try! NSRegularExpression(pattern: #"\bMol[A-Z]\w*\b"#)
    private static let organismPattern = try! NSRegularExpression(pattern: #"\bOrg[A-Z]\w*\b"#)
    private static let templatePattern = try! NSRegularExpression(pattern: #"\bTemplate[A-Z]\w*\b"#)

    private static let message = LintRuleMessage(
        rule: "Atomic layers only depend on the layers below them: _Primitives -> _Tokens -> Atoms -> Molecules -> Organisms -> Templates.",
        why: """
            Strict layering is what makes the design system composable: an atom can be reused anywhere because it
            depends on nothing above it, and a molecule never hides a whole organism inside.
            """,
        howToFix: "Move shared code down (to atoms or tokens) or composition up (to organisms or templates) so references follow the dependency flow.",
        badExample: "// in Atoms/Text/AtomText.swift\nMolButton(text: text, onTap: onTap)",
        goodExample: "// in Molecules/Button/MolButton.swift\nAtomText(text: text, textFontRole: .displayH4)"
    )
}

private extension SwiftSourceCode {
    func references(_ pattern: NSRegularExpression) -> Bool {
        source.containsMatch(of: pattern)
    }

    func referencesOtherMolecules() -> Bool {
        let ownNames = Set(structs().map(\.name) + enums().map(\.name) + classes().map(\.name))
        let pattern = try! NSRegularExpression(pattern: #"\bMol[A-Z]\w*\b"#)
        let matches = pattern.matches(in: source, range: NSRange(source.startIndex..., in: source))
        return matches.contains { match in
            guard let range = Range(match.range, in: source) else { return false }
            let name = String(source[range])
            return !ownNames.contains(name) && !name.hasSuffix("Role") && !name.hasSuffix("State")
        }
    }
}
