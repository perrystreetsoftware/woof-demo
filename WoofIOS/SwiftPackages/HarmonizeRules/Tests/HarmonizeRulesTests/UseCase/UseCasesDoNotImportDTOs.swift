import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class UseCasesDoNotImportDTOs: QuickSpec {
    override class func spec() {
        Given("A file in the use case or domain model package") {
            let files = WoofHarmonize.useCasePackage.sources() + WoofHarmonize.domainModelPackage.sources()

            Then("It does not import DTOs or data sources") {
                files.assertFalse(message: message) { file in
                    file.imports().contains { ["DTO", "DataSource", "DataSourceFakes"].contains($0.name) }
                }
            }
        }
    }

    private static let message = LintRuleMessage(
        rule: "The domain layer must not know about DTOs or data sources.",
        why: "DTOs mirror a transport format. If the domain depended on them, every server change would ripple through business rules.",
        howToFix: "Map the DTO to a domain model in the repository and consume the domain model.",
        badExample: "func callAsFunction() -> AnyPublisher<DogsPageDTO, DogsError>",
        goodExample: "func callAsFunction() -> AnyPublisher<DogsPage, DogsError>"
    )
}
