import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class RepositoriesDependOnDataSourceInterfaces: QuickSpec {
    override class func spec() {
        Given("A Repository") {
            let repositories = WoofHarmonize.repositories

            Then("It depends only on data source protocols and mappers") {
                repositories.assertTrue(rule: rule) { repository in
                    repository.variables
                        .filter { $0.isStored && $0.isConstant && $0.initializerClause == nil }
                        .allSatisfy { variable in
                            let type = variable.typeAnnotation?.name ?? ""
                            return type.hasSuffix("DataSourceImplementing") || type.hasSuffix("Mapper")
                        }
                }
            }
        }
    }

    private static let rule = Rule(
        description: "Repositories depend on *DataSourceImplementing protocols and mappers, never on concrete data sources or other repositories.",
        rationale: """
            Programming against the protocol is what lets tests swap the data source for a fake without
            mocks, and keeps repositories from reaching into each other's state.
            """,
        fixHint: "Inject the *DataSourceImplementing protocol. Compose repositories in a UseCase instead of injecting one into another.",
        badExample: "final class DogsRepository { private let dataSource: DogsLocalDataSource; private let moderation: ModerationRepository }",
        goodExample: "final class DogsRepository { private let dataSource: DogsDataSourceImplementing; private let pageMapper: DogsPageDTOToDomainMapper }"
    )
}
