import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class DataSourcesAreBackedByInterfaces: QuickSpec {
    override class func spec() {
        Given("A data source protocol") {
            let protocols = WoofHarmonize.dataSourcePackage.protocols().withNameContaining("DataSource")

            Then("Its name ends with DataSourceImplementing") {
                protocols.assertTrue(message: message) { $0.name.hasSuffix("DataSourceImplementing") }
            }
        }

        Given("A data source implementation") {
            let implementations = WoofHarmonize.dataSourcePackage.classes().withSuffix("DataSource")

            Then("It conforms to a *DataSourceImplementing protocol") {
                implementations.assertTrue(message: message) { implementation in
                    implementation.inheritanceTypesNames.contains { $0.hasSuffix("DataSourceImplementing") }
                }
            }
        }
    }

    private static let message = LintRuleMessage(
        rule: "Every data source is a *DataSourceImplementing protocol with local and fake implementations.",
        why: "The protocol is the seam between the app and the outside world. Repositories, tests, and a future remote implementation all program against it.",
        howToFix: "Declare protocol FooDataSourceImplementing, then make FooLocalDataSource and FakeFooDataSource conform to it.",
        badExample: "final class DogsLocalDataSource { func getDogs() -> AnyPublisher<DogsPageDTO, DataSourceError> }",
        goodExample: "protocol DogsDataSourceImplementing { func getDogs() -> AnyPublisher<DogsPageDTO, DataSourceError> }\nfinal class DogsLocalDataSource: DogsDataSourceImplementing"
    )
}
