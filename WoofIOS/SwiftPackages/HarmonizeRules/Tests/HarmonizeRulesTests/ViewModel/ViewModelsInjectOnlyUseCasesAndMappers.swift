import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class ViewModelsInjectOnlyUseCasesAndMappers: QuickSpec {
    override class func spec() {
        Given("A ViewModel initializer parameter") {
            let viewModels = WoofHarmonize.viewModels

            Then("Every dependency is a UseCase, a Mapper, the NavigatorImplementing, or a @DIArgument value") {
                viewModels.assertTrue(message: message) { viewModel in
                    let arguments = Set(viewModel.variables.filter { $0.hasAttribute(named: "@DIArgument") }.map(\.name))
                    return viewModel.initializers.flatMap(\.parameters).allSatisfy { parameter in
                        let type = parameter.typeAnnotation?.name ?? ""
                        return type.hasSuffix("UseCase")
                            || type.hasSuffix("Mapper")
                            || type == "NavigatorImplementing"
                            || arguments.contains(parameter.name)
                    }
                }
            }
        }
    }

    private static let message = LintRuleMessage(
        rule: "ViewModels may only inject UseCases, Mappers, NavigatorImplementing (for state-driven navigation), and @DIArgument values.",
        why: """
            ViewModels orchestrate use cases and shape their output for the UI. Any other dependency
            (repositories, data sources, framework classes) belongs to a lower layer.
            """,
        howToFix: "Move the work into a UseCase or a Mapper and inject that.",
        badExample: "final class ProfileWoofViewModel { private let woofsRepository: WoofsRepository }",
        goodExample: "final class ProfileWoofViewModel { @DIArgument private let dog: Dog; private let sendWoofUseCase: SendWoofUseCase }"
    )
}
