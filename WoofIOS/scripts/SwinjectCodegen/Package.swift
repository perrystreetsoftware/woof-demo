// swift-tools-version: 5.9

import PackageDescription

let package = Package(
    name: "SwinjectCodegen",
    targets: [
        .executableTarget(
            name: "SwinjectCodegen",
            path: "Sources"
        )
    ]
)
