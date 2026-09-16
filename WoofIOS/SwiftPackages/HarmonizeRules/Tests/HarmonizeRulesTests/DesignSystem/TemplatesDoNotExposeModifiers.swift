import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class TemplatesDoNotExposeModifiers: QuickSpec {
    override class func spec() {
        Given("A public Template view") {
            let templates = WoofHarmonize.atomicDesignPackage.structs().views.withPublicModifier().inFolder("Templates")

            Then("It does not take layout values such as CGFloat or EdgeInsets") {
                templates.assertFalse(rule: rule) { template in
                    template.initializers.flatMap(\.parameters).contains { parameter in
                        ["CGFloat", "EdgeInsets", "Double"].contains(parameter.typeAnnotation?.name.withoutGenerics ?? "")
                    }
                }
            }

            Then("It exposes at least one @ViewBuilder slot") {
                templates.assertTrue(rule: rule) { template in
                    template.initializers.flatMap(\.parameters).contains { $0.hasAttribute(named: "@ViewBuilder") }
                }
            }
        }
    }

    private static let rule = Rule(
        description: "Templates expose slots, never layout values.",
        rationale: "A template owns the page layout. If a screen could pass a padding or a size, paddings and sizes would again be decided outside the design system.",
        fixHint: "Build layout parameters into the template and expose @ViewBuilder slots for content.",
        badExample: "public struct TemplateGrid<Content: View>: View { public init(padding: CGFloat, @ViewBuilder content: () -> Content) }",
        goodExample: "public struct TemplateGrid<TopBar: View, Content: View>: View { public init(@ViewBuilder topBar: () -> TopBar, @ViewBuilder content: () -> Content) }"
    )
}
