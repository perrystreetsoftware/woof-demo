import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class AtomsDoNotComposeOtherAtoms: QuickSpec {
    override class func spec() {
        Given("An atom view") {
            let atoms = WoofHarmonize.atomicDesignPackage.structs().views.inFolder("Atoms")

            Then("It does not call another atom") {
                atoms.assertFalse(message: message) { atom in
                    atom.description.containsMatch(of: atomCallPattern)
                }
            }
        }
    }

    private static let atomCallPattern = try! NSRegularExpression(pattern: #"\bAtom[A-Z]\w*\("#)

    private static let message = LintRuleMessage(
        rule: "Atoms are built from SwiftUI primitives only; they never call other atoms.",
        why: "The moment two atoms combine, the result is a molecule. Keeping atoms leaf-level keeps the hierarchy honest and the dependency graph flat.",
        howToFix: "Promote the composition to a Mol* component in Atomic/Molecules.",
        badExample: "struct AtomLabeledIcon: View { var body: some View { HStack { AtomIcon(...); AtomText(...) } } }",
        goodExample: "struct MolLabeledIcon: View { var body: some View { HStack { AtomIcon(...); AtomText(...) } } }"
    )
}
