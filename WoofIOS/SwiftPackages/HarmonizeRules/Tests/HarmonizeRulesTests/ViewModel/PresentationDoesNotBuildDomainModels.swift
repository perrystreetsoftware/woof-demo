import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class PresentationDoesNotBuildDomainModels: QuickSpec {
    override class func spec() {
        Given("A presentation file outside previews") {
            let files = WoofHarmonize.presentationPackages.sources().withoutFolder("Preview")
            let models = WoofHarmonize.domainModelPackage.structs(includeNested: false).map(\.name)
            let construction = try! NSRegularExpression(pattern: #"(?<![\w.])("# + models.joined(separator: "|") + #")\("#)

            Then("It receives domain models from use cases instead of constructing them") {
                files.assertFalse(rule: rule) { $0.source.containsMatch(of: construction) }
            }
        }
    }

    private static let rule = Rule(
        description: "Presentation code receives domain models from use cases; only previews construct them.",
        rationale: "Domain models come from a data source through a repository and a use case. A view model that builds its own list skips those layers, so the screen shows data the app never loads, and its tests check the literals instead of the behaviour.",
        fixHint: "Add the data source, repository and use case the way the reference feature does, inject the use case into the view model and derive the state from it. Keep hand-made models in UI/Preview for previews and in DataSourceFakes for tests.",
        badExample: "// FavoritesViewModel\nJust([Dog(id: 1, name: \"Rufus\", photoUrl: \"\")]).map { ... }",
        goodExample: "// FavoritesViewModel\ngetFavoriteDogsUseCase().map { dogs in ... }"
    )
}
