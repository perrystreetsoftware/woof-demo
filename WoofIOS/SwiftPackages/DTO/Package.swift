// swift-tools-version: 5.10

import PackageDescription

let package = Package(
    name: "DTO",
    platforms: [.iOS("18.0"), .macOS("15.0")],
    products: [
        .library(name: "DTO", targets: ["DTO"])
    ],
    targets: [
        .target(name: "DTO")
    ]
)
