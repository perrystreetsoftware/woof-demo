import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class ViewModelsDoNotImportSwiftUI: QuickSpec {
    override class func spec() {
        Given("A ViewModel, UI model, or mapper file in a feature package") {
            let files = WoofHarmonize.featurePackages.sources().withSuffix("ViewModel", "UIModel", "UIModelMapper")

            Then("It does not import SwiftUI, the design system, or resources") {
                files.assertFalse(message: message) { file in
                    file.imports().contains { ["SwiftUI", "UIKit", "DesignSystem", "Resources"].contains($0.name) }
                }
            }
        }
    }

    private static let message = LintRuleMessage(
        rule: "ViewModels, UI models, and mappers must not import SwiftUI, design system, or resource types.",
        why: """
            Presentation logic must be testable without a UI framework and reusable across views. Localized strings
            and views are view concerns and belong in screen-layer extensions.
            """,
        howToFix: "Emit plain data in the UI model and map it to strings, icons, or colors in an extension under UI/Extensions.",
        badExample: "struct ProfileToastUIModel { let message: LocalizedStringKey }",
        goodExample: "enum ProfileToastUIModel { case woofSent(name: String) }\nextension ProfileToastUIModel { var text: String { ... } }"
    )
}
