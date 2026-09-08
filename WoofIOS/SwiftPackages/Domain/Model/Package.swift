// swift-tools-version: 5.10

import PackageDescription

let package = Package(
    name: "Model",
    platforms: [.iOS("18.0"), .macOS("15.0")],
    products: [
        .library(name: "Models", targets: ["Models"])
    ],
    targets: [
        .target(name: "Models")
    ]
)
