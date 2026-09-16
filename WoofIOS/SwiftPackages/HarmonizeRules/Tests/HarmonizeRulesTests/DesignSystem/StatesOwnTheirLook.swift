import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class StatesOwnTheirLook: QuickSpec {
    override class func spec() {
        Given("A State type in an atomic component") {
            let enums = WoofHarmonize.atomicDesignPackage.enums().withSuffix("State").withoutFolder("_Tokens")
            let structs = WoofHarmonize.atomicDesignPackage.structs().withSuffix("State").withoutFolder("_Tokens")
                .filter { !$0.inheritanceTypesNames.contains("View") }

            Then("A struct state stores only let properties") {
                structs.assertTrue(message: message) { state in
                    state.variables.filter(\.isStored).allSatisfy(\.isConstant)
                }
            }

            Then("It resolves its look from the theme, never from caller input") {
                enums.assertFalse(message: message) { $0.description.containsMatch(of: callerInputPattern) }
                structs.assertFalse(message: message) { $0.description.containsMatch(of: callerInputPattern) }
            }
        }

        Given("A State folder in an atomic component") {
            let files = WoofHarmonize.atomicDesignPackage.sources().inFolder("State").withoutFolder("_Tokens")

            Then("It does not import Resources") {
                files.assertFalse(message: message) { file in
                    file.imports().contains { $0.name == "Resources" }
                }
            }
        }
    }

    private static let callerInputPattern = try! NSRegularExpression(pattern: #"\bfunc\s+\w+\s*\((?!\s*\)|from theme: ThemeImplementing\))"#)

    private static let message = LintRuleMessage(
        rule: "A State is what a component shows at one moment, and the component owns how it looks.",
        why: "Whether it is a mode (ButtonState.loading, OverflowMenuState.expanded) or data with a flag (AsyncImageState), the design system resolves the look from theme tokens and roles, so callers describe what to show and never style it.",
        howToFix: "Make the type an enum (or a struct with let properties), resolve its look from theme tokens and roles, and pass icons and text as component parameters.",
        badExample: """
        // Organisms/OverflowMenu/OverflowMenuState.swift
        struct OverflowMenuState {
            var isExpanded: Bool
            let icon: ImageAsset
        }
        """,
        goodExample: """
        // Organisms/OverflowMenu/State/OverflowMenuState.swift
        enum OverflowMenuState {
            case `default`
            case expanded

            var colorRole: IconColorRole {
                switch self {
                case .default: .onScrim
                case .expanded: .primary
                }
            }
        }
        """
    )
}
