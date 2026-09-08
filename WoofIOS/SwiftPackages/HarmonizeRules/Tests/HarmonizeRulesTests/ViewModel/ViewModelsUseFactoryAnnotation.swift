import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class ViewModelsUseFactoryAnnotation: QuickSpec {
    override class func spec() {
        Given("A ViewModel in a feature package") {
            let viewModels = WoofHarmonize.viewModels

            Then("It is annotated with @Factory") {
                viewModels.assertTrue(message: message) { $0.hasAttribute(named: "@Factory") }
            }
        }
    }

    private static let message = LintRuleMessage(
        rule: "ViewModels must be annotated with @Factory.",
        why: """
            The Swinject codegen scans @Factory and generates the registration, so the
            DI graph stays uniform and nobody hand-edits containers when a ViewModel gains a dependency.
            """,
        howToFix: "Add @Factory to the ViewModel class and rerun scripts/SwinjectCodegen.",
        badExample: "final class GridViewModel: StateProducingViewModel<GridViewModel.State, DogsError>",
        goodExample: "@Factory\nfinal class GridViewModel: StateProducingViewModel<GridViewModel.State, DogsError>"
    )
}
