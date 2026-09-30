import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class DesignSystemIsFeatureAgnostic: QuickSpec {
    override class func spec() {
        Given("A name declared in the atomic design system") {
            let scope = WoofHarmonize.atomicDesignPackage
            let vocabulary = featureVocabulary()

            Then("Components, types, role entries and properties are named by their shape") {
                scope.structs().assertFalse(rule: rule) { $0.name.usesAny(of: vocabulary) }
                scope.classes().assertFalse(rule: rule) { $0.name.usesAny(of: vocabulary) }
                scope.enums().assertFalse(rule: rule) { $0.name.usesAny(of: vocabulary) }
                scope.protocols().assertFalse(rule: rule) { $0.name.usesAny(of: vocabulary) }
                scope.functions().assertFalse(rule: rule) { $0.name.usesAny(of: vocabulary) }
                scope.variables().assertFalse(rule: rule) { $0.name.usesAny(of: vocabulary) }
                scope.enums().flatMap(\.cases).assertFalse(rule: rule) { $0.name.usesAny(of: vocabulary) }
            }

            Then("Parameters are named by the data they hold") {
                (scope.functions().flatMap(\.parameters) + scope.initializers().flatMap(\.parameters))
                    .assertFalse(rule: rule) { $0.label.usesAny(of: vocabulary) || $0.name.usesAny(of: vocabulary) }
            }

            Then("Its folders are named by shape") {
                scope.sources().assertFalse(rule: rule) { file in
                    let path = file.filePath?.deletingLastPathComponent().path ?? ""
                    let folders = path.components(separatedBy: "/Atomic/").dropFirst().joined().split(separator: "/")
                    return folders.contains { String($0).usesAny(of: vocabulary) }
                }
            }

            Then("The resources it references belong to no feature") {
                scope.sources().assertFalse(rule: rule) { file in
                    let source = file.source
                    return resourcePattern.matches(in: source, range: NSRange(source.startIndex..., in: source)).contains { match in
                        guard let range = Range(match.range(at: 1), in: source) else { return false }
                        return source[range].split(separator: ".").contains { String($0).usesAny(of: vocabulary) }
                    }
                }
            }
        }
    }

    private static let resourcePattern = try! NSRegularExpression(pattern: #"\b(?:L10n|Asset)\.([\w.]+)"#)

    private static func featureVocabulary() -> Set<String> {
        let features = WoofHarmonize.featurePackages.sources().compactMap { file in
            file.filePath?.path.components(separatedBy: "/Presentation/").dropFirst().first?.split(separator: "/").first.map(String.init)
        }
        let models = WoofHarmonize.domainModelPackage.structs().map(\.name)
            + WoofHarmonize.domainModelPackage.enums().map(\.name).filter { !$0.hasSuffix("Error") }
        let entities = models.compactMap { $0.words().first }
        return Set((features + entities).flatMap { word -> [String] in
            let lowercased = word.lowercased()
            return [lowercased, lowercased.hasSuffix("s") ? String(lowercased.dropLast()) : lowercased]
        })
    }

    private static let rule = Rule(
        description: "The design system is feature-agnostic: names and the resources it references describe shape and data, never a feature.",
        rationale: "Every feature can use a design-system component, so it is named by what it looks like, its parameters by the data they hold, and its roles by the style they pick. A feature word in the design system, or a feature's string or icon inside a component, ties the component to one screen, and the next feature builds a twin instead of reusing it. Feature words are the feature package names and the domain model names.",
        fixHint: "Rename the component, its folder, role case or parameter after its structure or what it carries. Pass a feature's text and icons in as parameters; the feature's own words and resources stay in its presentation package.",
        badExample: "struct OrgFavoriteCard: View { init(dogName: String, photo: AsyncImageState, onFavoriteTap: @escaping () -> Void) }",
        goodExample: "struct OrgPhotoCard: View { init(title: String, imageState: AsyncImageState, contentDescription: String, onTap: @escaping () -> Void) }"
    )
}

private extension String {
    static let wordBoundary = try! NSRegularExpression(pattern: "(?<=[a-z0-9])(?=[A-Z])|_")

    func words() -> [String] {
        let marked = Self.wordBoundary.stringByReplacingMatches(in: self, range: NSRange(startIndex..., in: self), withTemplate: " ")
        return marked.split(separator: " ").map(String.init)
    }

    func usesAny(of vocabulary: Set<String>) -> Bool {
        words().contains { vocabulary.contains($0.lowercased()) }
    }
}
