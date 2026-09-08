import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class OnlyAdaptersInjectViewModels: QuickSpec {
    override class func spec() {
        Given("A view that owns a ViewModel with @StateObject") {
            let views = WoofHarmonize.presentationViews.filter { view in
                view.variables.contains { $0.hasAttribute(named: "@StateObject") && ($0.typeAnnotation?.name.hasSuffix("ViewModel") ?? false) }
            }

            Then("Its name ends with Adapter") {
                views.assertTrue(message: message) { $0.name.hasSuffix("Adapter") }
            }
        }
    }

    private static let message = LintRuleMessage(
        rule: "Only *Adapter views inject ViewModels.",
        why: """
            Adapters bind ViewModels to Screens. Screens stay pure functions of state so they can be
            previewed and reasoned about without dependency injection.
            """,
        howToFix: "Inject the ViewModel in an Adapter and pass state and callbacks down to the Screen.",
        badExample: "struct GridScreen: View { @StateObject private var viewModel: GridViewModel }",
        goodExample: "struct GridAdapter: View { @StateObject private var viewModel: GridViewModel\n    var body: some View { GridScreen(state: viewModel.state) } }"
    )
}
