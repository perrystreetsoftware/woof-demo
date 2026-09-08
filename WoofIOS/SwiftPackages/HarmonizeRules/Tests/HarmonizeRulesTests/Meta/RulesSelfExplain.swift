import Foundation
import Quick
import XCTest

final class RulesSelfExplain: QuickSpec {
    override class func spec() {
        Given("A Harmonize rule file") {
            let rulesDirectory = URL(fileURLWithPath: #filePath).deletingLastPathComponent().deletingLastPathComponent()
            let rules = swiftFiles(in: rulesDirectory).filter { !infrastructure.contains($0.lastPathComponent) }

            Then("It explains itself with a LintRuleMessage") {
                let violations = rules.filter { file in
                    let contents = (try? String(contentsOf: file, encoding: .utf8)) ?? ""
                    return !contents.contains("LintRuleMessage(")
                }
                XCTAssertTrue(violations.isEmpty, "\(message.formatted)\n\nViolating files:\n\(violations.map(\.lastPathComponent).joined(separator: "\n"))")
            }
        }
    }

    private static let infrastructure = ["GherkinUtils.swift", "HarmonizeUtils.swift", "LintRuleMessage.swift"]

    private static func swiftFiles(in directory: URL) -> [URL] {
        let enumerator = FileManager.default.enumerator(at: directory, includingPropertiesForKeys: nil)
        return enumerator?.compactMap { $0 as? URL }.filter { $0.pathExtension == "swift" } ?? []
    }

    private static let message = LintRuleMessage(
        rule: "Every lint rule uses LintRuleMessage for its assertion message.",
        why: "A failing rule must explain why it exists and how to fix it, otherwise the next person works around it instead of fixing the code.",
        howToFix: "Replace the raw string with a LintRuleMessage(rule, why, howToFix, badExample, goodExample).",
        badExample: "classes.assertTrue(message: \"Use @Factory\") { ... }",
        goodExample: "classes.assertTrue(message: message) { ... }"
    )
}
