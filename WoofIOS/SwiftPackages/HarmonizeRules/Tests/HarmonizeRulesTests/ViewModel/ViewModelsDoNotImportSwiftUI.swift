import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class ViewModelsDoNotImportSwiftUI: QuickSpec {
    override class func spec() {
        Given("A ViewModel, UI model, or mapper file in a feature package") {
            let files = WoofHarmonize.featurePackages.sources().withSuffix("ViewModel.swift", "UIModel.swift", "UIModelMapper.swift")

            Then("It does not import SwiftUI, the design system, or resources") {
                files.assertFalse(rule: rule) { file in
                    file.imports().contains { ["SwiftUI", "UIKit", "DesignSystem", "Resources"].contains($0.name) }
                }
            }
        }
    }

    private static let rule = Rule(
        description: "ViewModels, UI models, and mappers must not import SwiftUI, design system, or resource types.",
        rationale: """
            Presentation logic must be testable without a UI framework and reusable across views. Localized strings
            and views are view concerns and belong in screen-layer extensions.
            """,
        fixHint: "Emit plain data in the UI model and map it to strings, icons, or colors in an extension under UI/Extensions.",
        badExample: "struct ProfileToastUIModel { let message: LocalizedStringKey }",
        goodExample: "enum ProfileToastUIModel { case woofSent(name: String) }\nextension ProfileToastUIModel { var text: String { ... } }"
    )
}
