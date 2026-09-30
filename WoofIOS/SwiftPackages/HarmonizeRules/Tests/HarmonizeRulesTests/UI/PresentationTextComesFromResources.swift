import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class PresentationTextComesFromResources: QuickSpec {
    override class func spec() {
        Given("A function, initializer or property in a presentation package") {
            let scope = WoofHarmonize.presentationPackages
            let functions = scope.functions().withoutFolder("Preview")
            let initializers = scope.initializers().withoutFolder("Preview")
            let properties = scope.variables().withoutFolder("Preview").filter { property in
                !property.modifiers.contains(.static)
            }

            Then("It does not hardcode text") {
                functions.assertFalse(rule: rule) { $0.description.hasHardcodedText }
                initializers.assertFalse(rule: rule) { $0.description.hasHardcodedText }
                properties.assertFalse(rule: rule) { $0.description.hasHardcodedText }
            }
        }
    }

    private static let rule = Rule(
        description: "Text the user sees comes from string resources, never from literals in presentation code.",
        rationale: "Resources are where text is translated, pluralised and reviewed in one place. A literal can't be localised, and any value it formats belongs in the mapper or view model, where it is tested.",
        fixHint: "Add a string to Localizable.strings and read it through L10n. Compute the values the text shows in the mapper or view model and pass them to the resource.",
        badExample: "case .age(let ageInYears): return \"\\(ageInYears) years\"",
        goodExample: "case .age(let ageInYears): return L10n.Profile.age(ageInYears)"
    )
}

private extension String {
    static let literal = try! NSRegularExpression(pattern: #""((?:[^"\\]|\\.)*)""#)
    static let interpolation = try! NSRegularExpression(pattern: #"\\\([^)]*\)"#)
    static let letter = try! NSRegularExpression(pattern: "[A-Za-z]")

    var hasHardcodedText: Bool {
        Self.literal.matches(in: self, range: NSRange(startIndex..., in: self)).contains { match in
            guard let range = Range(match.range(at: 1), in: self) else { return false }
            let content = String(self[range])
            let withoutInterpolation = Self.interpolation.stringByReplacingMatches(
                in: content, range: NSRange(content.startIndex..., in: content), withTemplate: ""
            )
            return withoutInterpolation.containsMatch(of: Self.letter)
        }
    }
}
