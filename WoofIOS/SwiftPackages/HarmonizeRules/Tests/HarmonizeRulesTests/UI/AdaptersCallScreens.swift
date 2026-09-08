import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class AdaptersCallScreens: QuickSpec {
    override class func spec() {
        Given("A feature Adapter view") {
            let adapters = WoofHarmonize.adapterViews.withoutName("ErrorAdapter")

            Then("It renders a Screen view") {
                adapters.assertTrue(message: message) { $0.description.containsMatch(of: screenCallPattern) }
            }
        }
    }

    private static let screenCallPattern = try! NSRegularExpression(pattern: #"\b[A-Z]\w*Screen\("#)

    private static let message = LintRuleMessage(
        rule: "Every Adapter delegates rendering to a Screen view.",
        why: "The Adapter/Screen split only works if the Adapter is a thin bridge: observe state, bind callbacks, render the Screen.",
        howToFix: "Move layout code into a *Screen view and call it from the Adapter.",
        badExample: "struct GridAdapter: View { var body: some View { TemplateGrid(...) { ... } } }",
        goodExample: "struct GridAdapter: View { var body: some View { GridScreen(state: viewModel.state, onCellTap: ...) } }"
    )
}
