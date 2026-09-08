import Foundation
import Harmonize
import HarmonizeSemantics

enum WoofHarmonize {
    static let productionCode = Harmonize.productionCode()
    static let testCode = Harmonize.testCode()
    static let productionAndTestCode = Harmonize.productionAndTestCode()

    static let designSystemPackage = productionCode.on("SwiftPackages/DesignSystem")
    static let atomicDesignPackage = productionCode.on("SwiftPackages/DesignSystem/Sources/DesignSystem/Atomic")
    static let presentationPackages = productionCode.on("SwiftPackages/Presentation")
    static let featurePackages = productionCode.on("SwiftPackages/Presentation").excluding("Common", "Navigation")
    static let dtoPackage = productionCode.on("SwiftPackages/DTO")
    static let domainModelPackage = productionCode.on("SwiftPackages/Domain/Model")
    static let useCasePackage = productionCode.on("SwiftPackages/Domain/UseCase")
    static let repositoriesPackage = productionCode.on("SwiftPackages/Data/Repositories")
    static let dataSourcePackage = productionAndTestCode.on("SwiftPackages/Data/DataSource")

    static var viewModels: [Class] { featurePackages.classes().withSuffix("ViewModel") }
    static var useCases: [Class] { useCasePackage.classes().withSuffix("UseCase") }
    static var repositories: [Class] { repositoriesPackage.classes().withSuffix("Repository") }
    static var mappers: [Class] { productionCode.classes().withNameEndingWith("DTOToDomainMapper", "UIModelMapper") }
    static var views: [Struct] { productionCode.structs().views }
    static var presentationViews: [Struct] { presentationPackages.structs().views }
    static var featureViews: [Struct] { featurePackages.structs().views }
    static var screenViews: [Struct] { featureViews.withSuffix("Screen") }
    static var adapterViews: [Struct] { featureViews.withSuffix("Adapter") }

    static let diAnnotations = ["@Factory", "@Single", "@FactoryForProtocol", "@SingleForProtocol", "@FactoryCollection", "@MockApi"]
}

extension Array where Element == Struct {
    var views: [Struct] {
        filter { $0.inheritanceTypesNames.contains("View") || $0.inheritanceTypesNames.contains("ViewModifier") }
    }
}

extension Array where Element == SwiftSourceCode {
    func inFolder(_ folder: String) -> [SwiftSourceCode] {
        filter { $0.filePath?.path.contains("/\(folder)/") == true }
    }

    func withoutFolder(_ folder: String) -> [SwiftSourceCode] {
        filter { $0.filePath?.path.contains("/\(folder)/") == false }
    }
}

extension Array where Element: SyntaxNodeProviding & SourceCodeProviding {
    func inFolder(_ folder: String) -> [Element] {
        filter { $0.sourceCodeLocation.sourceFilePath?.path.contains("/\(folder)/") == true }
    }

    func withoutFolder(_ folder: String) -> [Element] {
        filter { $0.sourceCodeLocation.sourceFilePath?.path.contains("/\(folder)/") == false }
    }
}

extension SourceCodeProviding {
    var filePathString: String {
        sourceCodeLocation.sourceFilePath?.path ?? ""
    }
}

extension AttributesProviding {
    func hasAttribute(named name: String) -> Bool {
        attributes.contains { $0.name == name || "@" + $0.name == name }
    }
}

extension String {
    func containsMatch(of regex: NSRegularExpression) -> Bool {
        regex.firstMatch(in: self, range: NSRange(startIndex..., in: self)) != nil
    }

    var withoutGenerics: String {
        guard let index = firstIndex(of: "<") else { return self }
        return String(self[..<index])
    }
}
