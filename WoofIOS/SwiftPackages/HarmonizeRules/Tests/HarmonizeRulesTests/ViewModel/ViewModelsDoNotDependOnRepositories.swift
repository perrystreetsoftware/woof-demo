import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class ViewModelsDoNotDependOnRepositories: QuickSpec {
    override class func spec() {
        Given("A ViewModel file") {
            let files = WoofHarmonize.featurePackages.sources().withSuffix("ViewModel")

            Then("It does not import repositories or data sources") {
                files.assertFalse(message: message) { file in
                    file.imports().contains { ["Repositories", "DataSource", "DataSourceFakes"].contains($0.name) }
                }
            }
        }
    }

    private static let message = LintRuleMessage(
        rule: "ViewModels must not import repositories or data sources.",
        why: """
            The flow is View -> ViewModel -> UseCase -> Repository -> DataSource. Skipping the use case
            layer spreads business rules across ViewModels and makes them untestable in isolation.
            """,
        howToFix: "Wrap the repository call in a single-purpose UseCase and inject that instead.",
        badExample: "final class GridViewModel { private let dogsRepository: DogsRepository }",
        goodExample: "final class GridViewModel { private let getDogsFeedUseCase: GetDogsFeedUseCase }"
    )
}
