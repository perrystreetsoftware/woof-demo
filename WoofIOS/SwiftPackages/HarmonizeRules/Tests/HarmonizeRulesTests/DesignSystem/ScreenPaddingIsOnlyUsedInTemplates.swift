import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class ScreenPaddingIsOnlyUsedInTemplates: QuickSpec {
    override class func spec() {
        Given("A view that applies a screen padding role") {
            let views = WoofHarmonize.views.filter { $0.description.contains(screenPaddingUsage) }

            Then("It lives in the Templates folder") {
                views.assertTrue(message: message) { $0.filePathString.contains("/Templates/") }
            }
        }
    }

    private static let screenPaddingUsage = "PaddingRoles.Screen"

    private static let message = LintRuleMessage(
        rule: "Screen padding roles may only be applied by Templates.",
        why: """
            PaddingRoles.Screen describes the gutter between page content and the screen edge.
            Templates are the one layer that owns page level layout, so if an Atom, Molecule or
            Organism also applies it the gutter is added twice and drifts between screens.
            """,
        howToFix: """
            Let the Template apply the screen padding around its content, and use
            PaddingRoles.Element or SpacingRoles inside the component.
            """,
        badExample: """
            public struct OrgFooList: View {
                public var body: some View {
                    VStack { FooContent() }
                        .padding(.horizontal, PaddingRoles.Screen.regular.rawValue)
                }
            }
            """,
        goodExample: """
            public struct TemplateScrollableContent<Content: View>: View {
                public var body: some View {
                    ScrollView { content() }
                        .padding(.horizontal, PaddingRoles.Screen.regular.rawValue)
                }
            }
            """
    )
}
