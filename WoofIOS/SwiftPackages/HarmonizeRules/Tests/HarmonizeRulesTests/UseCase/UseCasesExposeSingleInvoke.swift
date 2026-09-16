import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class UseCasesExposeSingleInvoke: QuickSpec {
    override class func spec() {
        Given("A UseCase") {
            let useCases = WoofHarmonize.useCases

            Then("Its only public function is callAsFunction") {
                useCases.assertTrue(rule: rule) { useCase in
                    let publicFunctions = useCase.functions.withoutModifier(.private)
                    return publicFunctions.count == 1 && publicFunctions[0].name == "callAsFunction"
                }
            }

            Then("It exposes no public state") {
                useCases.assertTrue(rule: rule) { useCase in
                    useCase.variables.withPublicModifier().allSatisfy { $0.modifiers.contains(.static) && $0.isConstant }
                }
            }
        }
    }

    private static let rule = Rule(
        description: "UseCases expose exactly one callAsFunction() and no public state.",
        rationale: """
            One class, one use case. A single callAsFunction keeps the responsibility obvious and lets callers
            use the class like a function. Public state would turn it into a service.
            """,
        fixHint: "Split additional public functions into their own UseCases; move state into a Repository.",
        badExample: "final class DogsUseCase { func load() {}; func block() {} }",
        goodExample: "final class LoadNextDogsPageUseCase { func callAsFunction() -> AnyPublisher<DogsPage, DogsError> }"
    )
}
