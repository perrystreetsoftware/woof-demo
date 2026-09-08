// swift-tools-version: 5.10

import PackageDescription

let package = Package(
    name: "UseCase",
    platforms: [.iOS("18.0"), .macOS("15.0")],
    products: [
        .library(name: "UseCase", targets: ["UseCase"])
    ],
    dependencies: [
        .package(path: "../../Macros"),
        .package(path: "../Model"),
        .package(path: "../../Data/Repositories"),
        .package(url: "https://github.com/Swinject/Swinject.git", from: "2.9.1"),
        .package(url: "https://github.com/Swinject/SwinjectAutoregistration.git", from: "2.9.1"),
    ],
    targets: [
        .target(
            name: "UseCase",
            dependencies: [
                .product(name: "DI", package: "Macros"),
                .product(name: "Models", package: "Model"),
                "Repositories",
                "Swinject",
                "SwinjectAutoregistration",
            ]
        )
    ]
)
