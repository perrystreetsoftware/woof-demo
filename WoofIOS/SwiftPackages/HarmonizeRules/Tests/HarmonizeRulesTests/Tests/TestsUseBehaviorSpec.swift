import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class TestsUseBehaviorSpec: QuickSpec {
    override class func spec() {
        Given("A test class") {
            let tests = WoofHarmonize.testCode.classes().withSuffix("Test")

            Then("It is a QuickSpec that reads as Given / When / Then") {
                tests.assertTrue(rule: rule) { test in
                    test.inheritanceTypesNames.contains("QuickSpec")
                        && test.description.contains("Given(")
                        && test.description.contains("Then(")
                }
            }
        }

        Given("A test file") {
            let files = WoofHarmonize.testCode.sources()

            Then("It does not use XCTestCase, sleep, or the raw Quick DSL") {
                files.assertTrue(rule: rule) { file in
                    !file.source.contains("XCTestCase")
                        && !file.source.containsMatch(of: sleepPattern)
                        && !file.source.containsMatch(of: rawDSLPattern)
                }
            }
        }
    }

    private static let sleepPattern = try! NSRegularExpression(pattern: #"\b(sleep|usleep|Thread\.sleep)\("#)
    private static let rawDSLPattern = try! NSRegularExpression(pattern: #"^\s*(describe|context|it)\("#, options: [.anchorsMatchLines])

    private static let rule = Rule(
        description: "Tests are QuickSpecs written as Given / When / Then with beforeEach setup and justBeforeEach actions.",
        rationale: """
            Tests target the ViewModel layer only, which exercises use cases and repositories underneath.
            The BDD structure reads as a spec, and a fresh Container per beforeEach gives every Then a clean slate.
            """,
        fixHint: "Subclass QuickSpec, use Given/When/Then/And blocks, set up in beforeEach, act in justBeforeEach, and advance time with TimeAdvancingFactory(container).tick().",
        badExample: "final class GridViewModelTest: XCTestCase { func testLoads() { sleep(1) } }",
        goodExample: "final class GridViewModelTest: QuickSpec { override class func spec() { Given(\"I open the grid\") { Then(\"...\") { ... } } } }"
    )
}
