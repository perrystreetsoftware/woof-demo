import Foundation
import Harmonize
import HarmonizeSemantics
import Quick

final class DesignSystemDoesNotDependOnAppLayers: QuickSpec {
    override class func spec() {
        Given("A file in the design system") {
            let files = WoofHarmonize.designSystemPackage.sources()

            Then("It does not import domain models, use cases, repositories, presentation, DI, or image loading libraries") {
                files.assertFalse(message: message) { file in
                    file.imports().contains { forbiddenImports.contains($0.name) || $0.name.hasPrefix("Presentation") }
                }
            }
        }
    }

    private static let forbiddenImports = ["Models", "UseCase", "Repositories", "DataSource", "DTO", "Swinject", "SwinjectAutoregistration", "DI", "Utils", "Kingfisher", "SDWebImage"]

    private static let message = LintRuleMessage(
        rule: "The design system knows nothing about domain models, UI models, DI, or image loaders.",
        why: """
            Components take plain data (strings, images, roles). That is what makes them reusable across features
            and previewable without a running app. Image requests are built by the caller and passed in as an AsyncImageState.
            """,
        howToFix: "Accept primitive values, roles, or AsyncImageState instead of feature models, and load images in the presentation layer.",
        badExample: "public struct OrgPhotoCard: View { let dog: Dog }",
        goodExample: "public struct OrgPhotoCard: View { let title: String; let imageState: AsyncImageState; let onTap: () -> Void }"
    )
}
