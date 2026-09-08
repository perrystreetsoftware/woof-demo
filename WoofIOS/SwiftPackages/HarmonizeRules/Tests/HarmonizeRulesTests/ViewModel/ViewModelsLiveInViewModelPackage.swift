import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class ViewModelsLiveInViewModelPackage: QuickSpec {
    override class func spec() {
        Given("A ViewModel") {
            let viewModels = WoofHarmonize.viewModels

            Then("It is declared in the feature's ViewModel folder") {
                viewModels.assertTrue(message: message) { $0.filePathString.contains("/ViewModel/") }
            }
        }
    }

    private static let message = LintRuleMessage(
        rule: "ViewModels live in Presentation/<Feature>/Sources/<Target>/ViewModel.",
        why: "Each feature package has the same four folders (ViewModel, UIModel, Mapper, UI), so anyone can find a class by convention.",
        howToFix: "Move the class to the ViewModel folder of its feature.",
        badExample: "// Presentation/Grid/Sources/PresentationGrid/UI/GridViewModel.swift",
        goodExample: "// Presentation/Grid/Sources/PresentationGrid/ViewModel/GridViewModel.swift"
    )
}
