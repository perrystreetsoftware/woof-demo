import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class ViewModelActionsUsePresentTense: QuickSpec {
    override class func spec() {
        Given("A public action on a ViewModel") {
            let actions = WoofHarmonize.viewModels.flatMap(\.functions).withPublicModifier().withPrefix("on")

            Then("It is not named in the past tense") {
                actions.assertFalse(message: message) { $0.name.hasSuffix("ed") }
            }
        }
    }

    private static let message = LintRuleMessage(
        rule: "ViewModel actions are named on<Something><Verb>, never in the past tense.",
        why: """
            Actions describe what the user does right now (onWoofTap), not what already happened.
            One naming style keeps adapters and tests predictable.
            """,
        howToFix: "Rename onButtonTapped to onButtonTap, onTextChanged to onTextChange.",
        badExample: "func onWoofTapped()",
        goodExample: "func onWoofTap()"
    )
}
