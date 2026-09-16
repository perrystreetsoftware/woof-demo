import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class ScreensHaveDevicePreviews: QuickSpec {
    override class func spec() {
        Given("A Screen file") {
            let files = WoofHarmonize.featurePackages.sources().withSuffix("Screen.swift")

            Then("It declares at least one #Preview") {
                files.assertTrue(rule: rule) { $0.source.contains("#Preview") }
            }
        }

        Given("A #Preview in production code") {
            let files = WoofHarmonize.productionCode.sources().filter { $0.source.contains("#Preview") }

            Then("It takes its theme from a ThemedScreenPreview or ThemedPreview") {
                files.assertTrue(rule: rule) { file in
                    file.source.components(separatedBy: "#Preview").dropFirst().allSatisfy { preview in
                        preview.contains("ThemedScreenPreview(") || preview.contains("ThemedPreview(")
                    }
                }
            }
        }
    }

    private static let rule = Rule(
        description: "Every Screen has a #Preview wrapped in ThemedScreenPreview with an explicit theme.",
        rationale: "Previews are how a screen is reviewed in every theme without running the app. ThemedScreenPreview renders the screen with the light or dark WoofTheme.",
        fixHint: "Add a #Preview whose body is ThemedScreenPreview(theme: WoofTheme.light()) { Screen(...) }.",
        badExample: "#Preview { GridScreen(...) }",
        goodExample: "#Preview(\"Loaded\") { ThemedScreenPreview(theme: WoofTheme.light()) { GridScreen(...) } }"
    )
}
