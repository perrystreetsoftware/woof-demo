import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class ViewModelsDoNotDependOnRepositories: QuickSpec {
    override class func spec() {
        Given("A ViewModel file") {
            let files = WoofHarmonize.featurePackages.sources().withSuffix("ViewModel.swift")

            Then("It does not import repositories or data sources") {
                files.assertFalse(rule: rule) { file in
                    file.imports().contains { ["Repositories", "DataSource", "DataSourceFakes"].contains($0.name) }
                }
            }
        }
    }

    private static let rule = Rule(
        description: "ViewModels must not import repositories or data sources.",
        rationale: """
            The flow is View -> ViewModel -> UseCase -> Repository -> DataSource. Skipping the use case
            layer spreads business rules across ViewModels and makes them untestable in isolation.
            """,
        fixHint: "Wrap the repository call in a single-purpose UseCase and inject that instead.",
        badExample: "final class GridViewModel { private let dogsRepository: DogsRepository }",
        goodExample: "final class GridViewModel { private let getDogsFeedUseCase: GetDogsFeedUseCase }"
    )
}
