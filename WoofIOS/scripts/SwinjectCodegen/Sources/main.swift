import Foundation

func writeLayer(packageName: String, layerName: String, additionalImports: [String] = []) {
    guard let rootDirectory = findProjectRoot(from: FileManager.default.currentDirectoryPath) else {
        fatalError("Could not find project root (no .xcodeproj or .xcworkspace) from \(FileManager.default.currentDirectoryPath)")
    }
    let directory = "\(rootDirectory)/SwiftPackages/\(packageName)/Sources/\(layerName)"
    let swiftFiles = findSwiftFiles(in: directory)
    let result = generateAutoregisters(in: swiftFiles)
    let allImports = (additionalImports + result.additionalImports.sorted()).uniqued()
    let containerExtension = createContainerExtension(
        for: layerName,
        autoRegisters: result.registrations,
        additionalImports: allImports)
    writeToFile(containerExtension, to: "\(directory)/DI/Container+\(layerName)+Generated.swift")
}

writeLayer(packageName: "Utils", layerName: "Utils")
writeLayer(packageName: "Data/DataSource", layerName: "DataSource")
writeLayer(packageName: "Data/DataSource", layerName: "DataSourceFakes", additionalImports: ["DataSource", "Utils"])
writeLayer(packageName: "Data/Repositories", layerName: "Repositories")
writeLayer(packageName: "Domain/UseCase", layerName: "UseCase")
writeLayer(packageName: "Presentation/Navigation", layerName: "PresentationNavigation")
writeLayer(packageName: "Presentation/Grid", layerName: "PresentationGrid")
writeLayer(packageName: "Presentation/Profile", layerName: "PresentationProfile", additionalImports: ["PresentationNavigation"])
writeLayer(packageName: "Presentation/Favorites", layerName: "PresentationFavorites")
writeLayer(packageName: "Presentation/Account", layerName: "PresentationAccount")
writeLayer(packageName: "Presentation/Home", layerName: "PresentationHome", additionalImports: ["PresentationNavigation"])

for file: String in generatedFilePaths {
    runSwiftFormat(on: file)
}
