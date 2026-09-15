import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class CustomModifiersArePrivate: QuickSpec {
    override class func spec() {
        Given("A custom view modifier") {
            let modifiers = WoofHarmonize.productionCode.structs()
                .withPublicModifier()
                .filter { $0.inheritanceTypesNames.contains("ViewModifier") && !baseline.contains($0.name) }

            Then("It is not public") {
                modifiers.assertEmpty(message: message)
            }
        }

        Given("A View extension function") {
            let functions = WoofHarmonize.productionCode.extensions()
                .filter { $0.typeAnnotation?.name == "View" }
                .flatMap { viewExtension in
                    viewExtension.functions.filter { viewExtension.modifiers.contains(.public) || $0.modifiers.contains(.public) }
                }
                .filter { !baseline.contains($0.name) }

            Then("It is not public") {
                functions.assertEmpty(message: message)
            }
        }
    }

    private static let baseline: Set<String> = [
        "OrgAlertDialog",
        "orgAlertDialog",
        "ErrorAdapter",
        "errorAdapter",
    ]

    private static let message = LintRuleMessage(
        rule: "Custom view modifiers are private.",
        why: "A public modifier spreads styling outside the component that owns it, so callers restyle one-off instead of picking a variant.",
        howToFix: "Make the modifier private to its component. If several components need it, move them into that file as initializers of one component.",
        badExample: """
        public extension View {
            func atomPlaceholderShimmer() -> some View { modifier(AtomPlaceholderShimmer()) }
        }
        """,
        goodExample: """
        public struct AtomPlaceholder: View {
            public init(role: TextPlaceholderRole) { ... }
            public init(role: ShapePlaceholderRole) { ... }
        }

        private extension View {
            func placeholderShimmer(radius: CGFloat) -> some View { modifier(PlaceholderShimmer(radius: radius)) }
        }
        """
    )
}
