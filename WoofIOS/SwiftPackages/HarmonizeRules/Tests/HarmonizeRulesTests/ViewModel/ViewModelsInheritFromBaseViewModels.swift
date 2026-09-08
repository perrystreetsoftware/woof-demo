import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class ViewModelsInheritFromBaseViewModels: QuickSpec {
    override class func spec() {
        Given("A ViewModel") {
            let viewModels = WoofHarmonize.viewModels

            Then("It extends one of the shared base ViewModels") {
                viewModels.assertTrue(message: message) { viewModel in
                    viewModel.inheritanceTypesNames.contains { allowedParents.contains($0.withoutGenerics) }
                }
            }
        }
    }

    private static let allowedParents = ["StateProducingViewModel", "StateDerivingViewModel", "StatelessViewModel"]

    private static let message = LintRuleMessage(
        rule: "ViewModels must extend StateProducingViewModel, StateDerivingViewModel, or StatelessViewModel.",
        why: """
            The base classes own the cancellables, the error channel, the lifecycle hooks, and the published
            state contract. Extending ObservableObject directly loses all of that.
            """,
        howToFix: "Pick StateProducingViewModel when the ViewModel mutates state, StateDerivingViewModel when state is combined from streams, StatelessViewModel otherwise.",
        badExample: "final class GridViewModel: ObservableObject",
        goodExample: "final class GridViewModel: StateProducingViewModel<GridViewModel.State, DogsError>"
    )
}
