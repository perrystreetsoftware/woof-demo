// swift-tools-version: 5.10

import PackageDescription

let package = Package(
    name: "Common",
    platforms: [.iOS("18.0"), .macOS("15.0")],
    products: [
        .library(name: "PresentationCommon", targets: ["PresentationCommon"])
    ],
    dependencies: [
        .package(path: "../../DesignSystem"),
        .package(path: "../../Resources"),
    ],
    targets: [
        .target(
            name: "PresentationCommon",
            dependencies: ["DesignSystem", "Resources"]
        )
    ]
)
