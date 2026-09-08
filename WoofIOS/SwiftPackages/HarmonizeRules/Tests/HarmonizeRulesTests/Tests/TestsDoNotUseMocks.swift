import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class TestsDoNotUseMocks: QuickSpec {
    override class func spec() {
        Given("A test file") {
            let files = WoofHarmonize.testCode.sources()

            Then("It does not import a mocking library") {
                files.assertFalse(message: message) { file in
                    file.imports().contains { mockingLibraries.contains($0.name) }
                }
            }
        }
    }

    private static let mockingLibraries = ["Cuckoo", "Mockingbird", "SwiftyMocky", "OCMock", "Mockolo"]

    private static let message = LintRuleMessage(
        rule: "Tests never use mocking libraries.",
        why: """
            Mocks couple tests to implementation details and let broken collaborations pass. The data source layer
            has hand-written fakes configured through *DataSourceFactory builders, and everything above it runs for real.
            """,
        howToFix: "Configure the fake data source with a factory, e.g. DogsDataSourceFactory(container).withDogs(count: 450).",
        badExample: "let useCase = MockGetDogsFeedUseCase()",
        goodExample: "DogsDataSourceFactory(container).withDogs(count: 450)"
    )
}
