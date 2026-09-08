import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class AtomicComponentsUseLayerPrefix: QuickSpec {
    override class func spec() {
        Given("A public view in the atomic design system") {
            let views = WoofHarmonize.atomicDesignPackage.structs().views.withPublicModifier()

            Then("Its name carries the prefix of its layer") {
                views.assertTrue(message: message) { view in
                    layerPrefixes.contains { layer, prefix in
                        view.filePathString.contains("/Atomic/\(layer)/") && view.name.hasPrefix(prefix)
                    }
                }
            }
        }
    }

    private static let layerPrefixes = [("Atoms", "Atom"), ("Molecules", "Mol"), ("Organisms", "Org"), ("Templates", "Template")]

    private static let message = LintRuleMessage(
        rule: "Public views are prefixed by their layer: Atom*, Mol*, Org*, Template*.",
        why: "The prefix tells the caller which layer they are composing with, so a Screen can be checked for using only Template/Org/Mol/Atom at a glance.",
        howToFix: "Rename the view with the prefix of the folder it lives in, or move it to the right layer.",
        badExample: "// in Atomic/Molecules/Button/\npublic struct PrimaryButton: View",
        goodExample: "// in Atomic/Molecules/Button/\npublic struct MolButton: View"
    )
}
