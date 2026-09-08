import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class RepositoryFunctionsDoNotReturnDTOs: QuickSpec {
    override class func spec() {
        Given("A public member of a Repository") {
            let functions = WoofHarmonize.repositories.flatMap(\.functions).withPublicModifier()
            let variables = WoofHarmonize.repositories.flatMap(\.variables).withPublicModifier()

            Then("Its function return types never mention a DTO") {
                functions.assertFalse(message: message) { $0.returnClause?.description.contains("DTO") == true }
            }

            Then("Its property types never mention a DTO") {
                variables.assertFalse(message: message) { $0.typeAnnotation?.description.contains("DTO") == true }
            }
        }
    }

    private static let message = LintRuleMessage(
        rule: "Repositories never expose DTOs.",
        why: "The repository is the boundary where transport data becomes domain data. Leaking a DTO pushes mapping into every caller.",
        howToFix: "Map with a DTOToDomainMapper inside the repository and return the domain model.",
        badExample: "func getDogs() -> AnyPublisher<DogsPageDTO, DogsError>",
        goodExample: "func getDogs() -> AnyPublisher<DogsPage, DogsError> { dataSource.getDogs().map { pageMapper($0) } }"
    )
}
