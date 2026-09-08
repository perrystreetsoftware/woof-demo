import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class TemplatesDoNotExposeModifiers: QuickSpec {
    override class func spec() {
        Given("A public Template view") {
            let templates = WoofHarmonize.atomicDesignPackage.structs().views.withPublicModifier().inFolder("Templates")

            Then("It does not take layout values such as CGFloat or EdgeInsets") {
                templates.assertFalse(message: message) { template in
                    template.initializers.flatMap(\.parameters).contains { parameter in
                        ["CGFloat", "EdgeInsets", "Double"].contains(parameter.typeAnnotation?.name.withoutGenerics ?? "")
                    }
                }
            }

            Then("It exposes at least one @ViewBuilder slot") {
                templates.assertTrue(message: message) { template in
                    template.initializers.flatMap(\.parameters).contains { $0.hasAttribute(named: "@ViewBuilder") }
                }
            }
        }
    }

    private static let message = LintRuleMessage(
        rule: "Templates expose slots, never layout values.",
        why: "A template owns the page layout. If a screen could pass a padding or a size, paddings and sizes would again be decided outside the design system.",
        howToFix: "Build layout parameters into the template and expose @ViewBuilder slots for content.",
        badExample: "public struct TemplateGrid<Content: View>: View { public init(padding: CGFloat, @ViewBuilder content: () -> Content) }",
        goodExample: "public struct TemplateGrid<TopBar: View, Content: View>: View { public init(@ViewBuilder topBar: () -> TopBar, @ViewBuilder content: () -> Content) }"
    )
}
