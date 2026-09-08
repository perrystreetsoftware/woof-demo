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
                viewModels.assertTrue(message: message) { testClassNames.contains($0.name + "Test") }
            }
        }
    }

    private static let message = LintRuleMessage(
        rule: "Every ViewModel has a <Name>Test.",
        why: "The ViewModel test is the single place where a feature's behaviour is specified end to end, from user action down to the fake data source.",
        howToFix: "Add <Name>Test as a QuickSpec in the feature's test target.",
        badExample: "final class ProfileWoofViewModel  // no ProfileWoofViewModelTest",
        goodExample: "final class ProfileWoofViewModelTest: QuickSpec"
    )
}
