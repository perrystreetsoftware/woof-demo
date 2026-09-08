import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class ScreensHaveDevicePreviews: QuickSpec {
    override class func spec() {
        Given("A Screen file") {
            let files = WoofHarmonize.featurePackages.sources().withSuffix("Screen")

            Then("It declares at least one #Preview") {
                files.assertTrue(message: message) { $0.source.contains("#Preview") }
            }
        }

        Given("A #Preview in production code") {
            let files = WoofHarmonize.productionCode.sources().filter { $0.source.contains("#Preview") }

            Then("It takes its theme from a ThemedScreenPreview or ThemedPreview") {
                files.assertTrue(message: message) { file in
                    file.source.components(separatedBy: "#Preview").dropFirst().allSatisfy { preview in
                        preview.contains("ThemedScreenPreview(") || preview.contains("ThemedPreview(")
                    }
                }
            }
        }
    }

    private static let message = LintRuleMessage(
        rule: "Every Screen has a #Preview wrapped in ThemedScreenPreview with an explicit theme.",
        why: "Previews are how a screen is reviewed in every theme without running the app. ThemedScreenPreview renders the screen with the light or dark WoofTheme.",
        howToFix: "Add a #Preview whose body is ThemedScreenPreview(theme: WoofTheme.light()) { Screen(...) }.",
        badExample: "#Preview { GridScreen(...) }",
        goodExample: "#Preview(\"Loaded\") { ThemedScreenPreview(theme: WoofTheme.light()) { GridScreen(...) } }"
    )
}
