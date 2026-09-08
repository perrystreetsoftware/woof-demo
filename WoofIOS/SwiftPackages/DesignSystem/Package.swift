// swift-tools-version: 5.10

import PackageDescription

let package = Package(
    name: "DesignSystem",
    platforms: [.iOS("18.0"), .macOS("15.0")],
    products: [
        .library(name: "DesignSystem", targets: ["DesignSystem"])
    ],
    dependencies: [
        .package(path: "../Resources")
    ],
    targets: [
        .target(
            name: "DesignSystem",
            dependencies: ["Resources"]
        )
    ]
)
