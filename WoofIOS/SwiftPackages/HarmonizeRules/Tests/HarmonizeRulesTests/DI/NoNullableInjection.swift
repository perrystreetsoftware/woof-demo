import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class NoNullableInjection: QuickSpec {
    override class func spec() {
        Given("An injected dependency of a Swinject-managed class") {
            let classes = WoofHarmonize.productionCode.classes().filter { klass in
                WoofHarmonize.diAnnotations.contains { klass.hasAttribute(named: $0) }
            }

            Then("Its stored constants are not optional") {
                classes.assertTrue(message: message) { klass in
                    klass.variables
                        .filter { $0.isStored && $0.isConstant && !$0.modifiers.contains(.static) }
                        .allSatisfy { !$0.isOptional }
                }
            }

            Then("Its initializer parameters are not optional") {
                classes.assertTrue(message: message) { klass in
                    klass.initializers.flatMap(\.parameters).allSatisfy { $0.typeAnnotation?.isOptional == false }
                }
            }
        }
    }

    private static let message = LintRuleMessage(
        rule: "Swinject-managed classes never inject nullable dependencies.",
        why: "A nullable dependency is a missing registration in disguise. Either the dependency exists in the graph or the design is wrong.",
        howToFix: "Make the dependency non-optional and provide it in the DI graph, or split the class.",
        badExample: "@Single\nfinal class DogsRepository { private let dataSource: DogsDataSourceImplementing? }",
        goodExample: "@Single\nfinal class DogsRepository { private let dataSource: DogsDataSourceImplementing }"
    )
}
