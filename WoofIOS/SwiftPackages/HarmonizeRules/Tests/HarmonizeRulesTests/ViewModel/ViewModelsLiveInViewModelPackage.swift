import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class ViewModelsLiveInViewModelPackage: QuickSpec {
    override class func spec() {
        Given("A ViewModel") {
            let viewModels = WoofHarmonize.viewModels

            Then("It is declared in the feature's ViewModel folder") {
                viewModels.assertTrue(rule: rule) { $0.filePathString.contains("/ViewModel/") }
            }
        }
    }

    private static let rule = Rule(
        description: "ViewModels live in Presentation/<Feature>/Sources/<Target>/ViewModel.",
        rationale: "Each feature package has the same four folders (ViewModel, UIModel, Mapper, UI), so anyone can find a class by convention.",
        fixHint: "Move the class to the ViewModel folder of its feature.",
        badExample: "// Presentation/Grid/Sources/PresentationGrid/UI/GridViewModel.swift",
        goodExample: "// Presentation/Grid/Sources/PresentationGrid/ViewModel/GridViewModel.swift"
    )
}
