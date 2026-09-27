import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class DIModulesAreRegistered: QuickSpec {
    override class func spec() {
        Given("A generated injection function in production code") {
            let injections = WoofHarmonize.productionCode.functions()
                .filter { $0.name.hasPrefix("inject") && $0.name.hasSuffix("Generated") }
                .withoutFolder("DataSourceFakes")
            let registry = WoofHarmonize.productionCode.sources()
                .first { $0.filePath?.path.hasSuffix("Woof/DI/Container+Extensions.swift") == true }?
                .source ?? ""

            Then("It is called in Container+Extensions") {
                injections.assertTrue(rule: rule) { registry.contains(".\($0.name)()") }
            }
        }
    }

    private static let rule = Rule(
        description: "Every generated injection function is called in Container+Extensions.",
        rationale: "An unregistered package compiles and passes its own view-model tests, which register the package directly, then crashes on a missing registration the first time the screen opens.",
        fixHint: "Add .inject<Name>Generated() to injectEverythingForProduction() in Woof/DI/Container+Extensions.swift and the package product to the Woof target.",
        badExample: """
        // Presentation/Favorites/Sources/PresentationFavorites/DI/Container+PresentationFavorites+Generated.swift
        func injectPresentationFavoritesGenerated() -> Container { ... }
        // …and nothing in Container+Extensions
        """,
        goodExample: """
        func injectEverythingForProduction() -> Container {
            self
                .injectPresentationFavoritesGenerated()
        }
        """
    )
}
