// swift-tools-version: 5.10

import PackageDescription

let package = Package(
    name: "Resources",
    defaultLocalization: "en",
    platforms: [.iOS("18.0"), .macOS("15.0")],
    products: [
        .library(name: "Resources", targets: ["Resources"])
    ],
    targets: [
        .target(
            name: "Resources",
            resources: [
                .process("Resources"),
                .copy("Dogs"),
            ]
        )
    ]
)
