import Foundation
import Harmonize
import Quick

final class RulesSelfExplain: QuickSpec {
    override class func spec() {
        Given("A Harmonize rule file") {
            let rulesDirectory = URL(fileURLWithPath: #filePath).deletingLastPathComponent().deletingLastPathComponent()
            let rules = swiftFiles(in: rulesDirectory).filter { !infrastructure.contains($0.lastPathComponent) }

            Then("It explains itself with a Rule") {
                let violations = rules.filter { file in
                    let contents = (try? String(contentsOf: file, encoding: .utf8)) ?? ""
                    return !contents.contains("Rule(")
                }.map { file in
                    Violation(
                        name: file.lastPathComponent,
                        message: rule.rationale,
                        filePath: file,
                        line: 1,
                        column: 1,
                        fileID: "HarmonizeRulesTests/\(file.lastPathComponent)",
                        rule: rule
                    )
                }
                guard !violations.isEmpty else { return }
                HarmonizeReporting.reporter.report(
                    violations: violations,
                    summary: rule.formatted,
                    at: AssertionLocation(fileID: #fileID, filePath: #filePath, line: #line, column: #column)
                )
            }
        }
    }

    private static let infrastructure = ["GherkinUtils.swift", "HarmonizeUtils.swift", "WoofRuleCategory.swift"]

    private static func swiftFiles(in directory: URL) -> [URL] {
        let enumerator = FileManager.default.enumerator(at: directory, includingPropertiesForKeys: nil)
        return enumerator?.compactMap { $0 as? URL }.filter { $0.pathExtension == "swift" } ?? []
    }

    private static let rule = Rule(
        title: "Every lint rule passes a Rule to its assertion.",
        category: WoofRuleCategory.meta,
        rationale: "A failing rule must explain why it exists and how to fix it, otherwise the next person works around it instead of fixing the code.",
        howToFix: "Replace the raw string with a Rule(title, category, rationale, howToFix, badExample, goodExample).",
        badExample: "classes.assertTrue(message: \"Use @Factory\") { ... }",
        goodExample: "classes.assertTrue(rule: rule) { ... }"
    )
}
