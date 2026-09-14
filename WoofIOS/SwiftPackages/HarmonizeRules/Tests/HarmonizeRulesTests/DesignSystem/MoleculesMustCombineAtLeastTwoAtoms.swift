import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class MoleculesMustCombineAtLeastTwoAtoms: QuickSpec {
    override class func spec() {
        Given("A molecule view") {
            let molecules = WoofHarmonize.atomicDesignPackage.structs().views
                .inFolder("Molecules")
                .filter { $0.name.hasPrefix("Mol") && !baseline.contains($0.name) }

            Then("It combines at least two atoms") {
                molecules.assertTrue(message: message) { molecule in
                    let source = molecule.description
                    let atomUsages = atomCallPattern.numberOfMatches(in: source, range: NSRange(source.startIndex..., in: source))
                    let iterations = iterationPattern.numberOfMatches(in: source, range: NSRange(source.startIndex..., in: source))
                    return atomUsages + iterations >= 2
                }
            }
        }
    }

    private static let baseline: Set<String> = [
        "MolButton",
        "MolButtonCompact",
        "MolIconButton",
        "MolTypeBar",
    ]

    private static let atomCallPattern = try! NSRegularExpression(pattern: #"\bAtom[A-Z]\w*\("#)
    private static let iterationPattern = try! NSRegularExpression(pattern: #"\bForEach\b"#)

    private static let message = LintRuleMessage(
        rule: "Molecules combine at least two atoms.",
        why: "A molecule exists to compose atoms. One atom in a wrapper adds nothing the atom did not already do.",
        howToFix: "Combine a second atom, or delete the molecule and call the atom directly. The same atom repeated counts.",
        badExample: """
        public struct MolTitle: View {
            public var body: some View {
                AtomText(text: title, textFontRole: .displayH4, colorRole: .onSurface)
            }
        }
        """,
        goodExample: """
        public struct MolTitleSubtitle: View {
            public var body: some View {
                AtomText(text: title, textFontRole: .displayH4, colorRole: .onSurface)
                AtomText(text: subtitle, textFontRole: .subheadP2, colorRole: .onSurfaceVariant)
            }
        }
        """
    )
}
