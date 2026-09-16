import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class ViewModelsHaveTests: QuickSpec {
    override class func spec() {
        Given("A ViewModel") {
            let viewModels = WoofHarmonize.viewModels
            let testClassNames = Set(WoofHarmonize.testCode.classes().map(\.name))

            Then("A matching *ViewModelTest exists") {
                viewModels.assertTrue(rule: rule) { testClassNames.contains($0.name + "Test") }
            }
        }
    }

    private static let rule = Rule(
        description: "Every ViewModel has a <Name>Test.",
        rationale: "The ViewModel test is the single place where a feature's behaviour is specified end to end, from user action down to the fake data source.",
        fixHint: "Add <Name>Test as a QuickSpec in the feature's test target.",
        badExample: "final class ProfileWoofViewModel  // no ProfileWoofViewModelTest",
        goodExample: "final class ProfileWoofViewModelTest: QuickSpec"
    )
}
