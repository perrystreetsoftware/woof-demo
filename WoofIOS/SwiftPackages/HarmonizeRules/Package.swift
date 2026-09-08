// swift-tools-version: 5.10

import PackageDescription

let package = Package(
    name: "HarmonizeRules",
    platforms: [.iOS("18.0"), .macOS("15.0")],
    products: [
        .library(name: "HarmonizeRules", targets: ["HarmonizeRules"])
    ],
    dependencies: [
        .package(url: "https://github.com/perrystreetsoftware/Harmonize.git", from: "1.2.1"),
        .package(url: "https://github.com/Quick/Quick.git", from: "7.6.2"),
        .package(url: "https://github.com/Quick/Nimble.git", from: "13.7.1"),
    ],
    targets: [
        .target(name: "HarmonizeRules"),
        .testTarget(
            name: "HarmonizeRulesTests",
            dependencies: [
                "HarmonizeRules",
                "Quick",
                "Nimble",
                .product(name: "Harmonize", package: "Harmonize"),
            ]
        ),
    ]
)
