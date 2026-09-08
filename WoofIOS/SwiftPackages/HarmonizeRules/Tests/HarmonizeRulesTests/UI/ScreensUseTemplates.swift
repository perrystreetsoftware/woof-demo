import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class ScreensUseTemplates: QuickSpec {
    override class func spec() {
        Given("A Screen view") {
            let screens = WoofHarmonize.screenViews

            Then("It renders a Template or delegates to another Screen") {
                screens.assertTrue(message: message) { $0.description.containsMatch(of: templateOrScreenCallPattern) }
            }
        }
    }

    private static let templateOrScreenCallPattern = try! NSRegularExpression(pattern: #"\b(Template\w+|[A-Z]\w*Screen)\("#)

    private static let message = LintRuleMessage(
        rule: "Screen views use a Template* as their root layout.",
        why: "Templates own page structure (top bar, insets, paddings, scrolling). Screens only fill their slots, so every screen shares the same skeleton.",
        howToFix: "Wrap the screen content in an existing Template, or add a new Template to the design system.",
        badExample: "struct GridScreen: View { var body: some View { ScrollView { LazyVGrid { ... } } } }",
        goodExample: "struct GridScreen: View { var body: some View { TemplateGrid(topBar: { OrgNavigationHeaderBranded() }) { ... } } }"
    )
}
