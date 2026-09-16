import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class ViewLayerDoesNotUseIfStatements: QuickSpec {
    override class func spec() {
        Given("A view in a presentation package") {
            let views = WoofHarmonize.presentationViews

            Then("It does not branch with if or optional map") {
                views.assertFalse(rule: rule) { view in
                    view.description.containsMatch(of: ifPattern) || view.description.contains("?.map {")
                }
            }
        }
    }

    private static let ifPattern = try! NSRegularExpression(pattern: #"\bif\s"#)

    private static let rule = Rule(
        description: "Views in presentation packages do not use if statements or optional map.",
        rationale: """
            Branching in the view hides state decisions from the ViewModel and its tests. Enum state plus
            exhaustive switch, or an early viewGuard, keeps every UI branch modelled and tested.
            """,
        fixHint: "Model the decision in the ViewModel state and switch on it, or use viewGuard(value) { ... } for nullability checks.",
        badExample: "if let dialog = state.dialog { OrgAlertDialog(...) }",
        goodExample: "viewGuard(state.dialog) { dialog in OrgAlertDialog(...) }"
    )
}
