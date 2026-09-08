import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class ViewModelsDoNotSubscribeInInitializers: QuickSpec {
    override class func spec() {
        Given("A ViewModel initializer") {
            let initializers = WoofHarmonize.viewModels.flatMap(\.initializers)

            Then("It does not start subscriptions") {
                initializers.assertFalse(message: message) { initializer in
                    initializer.body?.content.containsMatch(of: subscriptionPattern) == true
                }
            }
        }
    }

    private static let subscriptionPattern = try! NSRegularExpression(pattern: #"\.(sink|pss_sink|store|assign)\("#)

    private static let message = LintRuleMessage(
        rule: "ViewModels must not start subscriptions in init.",
        why: """
            init runs while Swinject constructs the ViewModel, before any view appears. Work started there
            cannot be tied to the screen lifecycle and makes tests set up state they never asked for.
            """,
        howToFix: "Override onFirstAppear() for one-time setup or onEveryAppear() for recurring setup; derive state by passing a publisher to StateDerivingViewModel.",
        badExample: "init(...) { loadDogs().sink { ... }.store(in: &cancellables) }",
        goodExample: "override func onFirstAppear() { loadDogs().sink { ... }.store(in: &cancellables) }"
    )
}
