import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class OrganismsReuseExistingOrganisms: QuickSpec {
    override class func spec() {
        Given("An organism in the atomic design system") {
            let views = WoofHarmonize.atomicDesignPackage.structs().views
            let components = Set(views.map(\.name).filter { $0.hasPrefix("Atom") || $0.hasPrefix("Mol") || $0.hasPrefix("Org") })
            let organismViews = views.inFolder("Organisms")
            let helpers = Dictionary(
                organismViews.filter { $0.modifiers.contains(.private) || $0.modifiers.contains(.fileprivate) }.map { ($0.name, $0) },
                uniquingKeysWith: { first, _ in first }
            )
            let organisms = organismViews.filter { $0.name.hasPrefix("Org") && helpers[$0.name] == nil }
            let parts = Dictionary(
                organisms.map { ($0.name, componentCalls(in: $0, components: components, helpers: helpers)) },
                uniquingKeysWith: { first, _ in first }
            )
            let rebuilt = organisms.flatMap { organism in
                parts.filter { existing, existingParts in
                    let calls = parts[organism.name] ?? [:]
                    return existing != organism.name
                        && existingParts.values.reduce(0, +) >= 2
                        && calls[existing] == nil
                        && existingParts.allSatisfy { component, count in (calls[component] ?? 0) >= count }
                }
                .map { (organism: organism.name, existing: $0.key) }
            }

            Then("It calls every existing organism whose parts it contains") {
                organisms.assertFalse(rule: rule(rebuilt: rebuilt)) { organism in
                    rebuilt.contains { $0.organism == organism.name }
                }
            }
        }
    }

    private static let callPattern = try! NSRegularExpression(pattern: #"\b([A-Z]\w*)\("#)

    private static func calls(in source: String) -> [String] {
        callPattern.matches(in: source, range: NSRange(source.startIndex..., in: source)).compactMap { match in
            Range(match.range(at: 1), in: source).map { String(source[$0]) }
        }
    }

    private static func componentCalls(in view: Struct, components: Set<String>, helpers: [String: Struct]) -> [String: Int] {
        let expanded = calls(in: view.description).filter { $0 != view.name }.flatMap { call in
            helpers[call].map { calls(in: $0.description) } ?? [call]
        }
        return expanded
            .filter { components.contains($0) && helpers[$0] == nil }
            .reduce(into: [:]) { counts, component in counts[component, default: 0] += 1 }
    }

    private static func rule(rebuilt: [(organism: String, existing: String)]) -> Rule {
        Rule(
            description: "An organism that contains every part of an existing organism calls that organism instead of rebuilding it.",
            rationale: "Two organisms made of the same parts drift apart: a fix or a new size lands in one and not the other, and the design system grows a near-twin for every screen. Calling the existing organism keeps one source for that shape.",
            fixHint: "Call the existing organism inside the new one and add only what it lacks. Where the new one looks different, such as another size or typography, add a case to the existing organism's Roles enum and pass it. "
                + rebuilt.map { "\($0.organism) rebuilds \($0.existing)." }.joined(separator: " "),
            badExample: """
            struct OrgErrorState: View {
                var body: some View {
                    VStack {
                        AtomAppLogo(...); AtomText(text: title, ...); AtomText(text: message, ...)
                        MolButton(text: actionText, onTap: onActionTap)
                    }
                }
            }
            // OrgEmptyState already draws AtomAppLogo + AtomText + AtomText
            """,
            goodExample: """
            struct OrgErrorState: View {
                var body: some View {
                    VStack {
                        OrgEmptyState(title: title, message: message)
                        MolButton(text: actionText, onTap: onActionTap)
                    }
                }
            }
            """
        )
    }
}
