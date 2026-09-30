import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class NoRawSwiftUIComponents: QuickSpec {
    override class func spec() {
        Given("A molecule, organism or template view") {
            let views = WoofHarmonize.atomicDesignPackage.structs()
                .filter { $0.inheritanceTypesNames.contains("View") }
                .filter { view in aboveAtoms.contains { view.filePathString.contains("/\($0)/") } }

            Then("It uses the atoms instead of the raw SwiftUI components they wrap") {
                views.assertFalse(rule: rule) { $0.description.containsMatch(of: rawComponentPattern) }
            }
        }
    }

    private static let aboveAtoms = ["Molecules", "Organisms", "Templates"]
    private static let rawComponentPattern = try! NSRegularExpression(
        pattern: #"(?<![\w.])(Text|Image|Divider|ProgressView|TextField|SecureField)\("#
    )

    private static let rule = Rule(
        description: "Molecules, organisms and templates build on atoms, never on the raw SwiftUI components the atoms wrap.",
        rationale: "Each atom owns one element's tokens, states and accessibility. A molecule or organism that draws Text, an image, a divider or a progress indicator itself re-implements that atom and bypasses its tokens, so the two drift apart.",
        fixHint: "Replace the raw component with the atom that wraps it (AtomText, AtomIcon, AtomPainterImage, AtomTextField, AtomHorizontalDivider, AtomCircularProgressIndicator). If no atom covers it, add one. Layout (VStack, HStack, ZStack, Spacer), Button and system presentation such as alerts stay as they are.",
        badExample: """
        struct MolTitleSubtitle: View {
            var body: some View { VStack { Text(title); Text(subtitle) } }
        }
        """,
        goodExample: """
        struct MolTitleSubtitle: View {
            var body: some View {
                VStack {
                    AtomText(text: title, textFontRole: .subheadP1)
                    AtomText(text: subtitle, textFontRole: .bodyP2)
                }
            }
        }
        """
    )
}
