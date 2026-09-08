import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class UseCasesDependOnlyOnRepositoriesAndUseCases: QuickSpec {
    override class func spec() {
        Given("A UseCase") {
            let useCases = WoofHarmonize.useCases

            Then("It only injects repositories and other use cases") {
                useCases.assertTrue(message: message) { useCase in
                    useCase.variables
                        .filter { $0.isStored && !$0.modifiers.contains(.static) }
                        .allSatisfy { variable in
                            let type = variable.typeAnnotation?.name ?? ""
                            return type.hasSuffix("Repository") || type.hasSuffix("UseCase")
                        }
                }
            }
        }
    }

    private static let message = LintRuleMessage(
        rule: "UseCases may only depend on Repositories and other UseCases.",
        why: "Use cases compose data access into business rules. Data sources, mappers, or view types would leak other layers into the domain.",
        howToFix: "Inject a Repository that wraps the data source, or another UseCase.",
        badExample: "final class SendWoofUseCase { private let dataSource: WoofsDataSourceImplementing }",
        goodExample: "final class SendWoofUseCase { private let woofsRepository: WoofsRepository }"
    )
}
