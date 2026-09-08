// swift-tools-version: 5.10

import PackageDescription

let package = Package(
    name: "Repositories",
    platforms: [.iOS("18.0"), .macOS("15.0")],
    products: [
        .library(name: "Repositories", targets: ["Repositories"])
    ],
    dependencies: [
        .package(path: "../../Macros"),
        .package(path: "../../Utils"),
        .package(path: "../../DTO"),
        .package(path: "../../Domain/Model"),
        .package(path: "../DataSource"),
        .package(url: "https://github.com/Swinject/Swinject.git", from: "2.9.1"),
        .package(url: "https://github.com/Swinject/SwinjectAutoregistration.git", from: "2.9.1"),
    ],
    targets: [
        .target(
            name: "Repositories",
            dependencies: [
                .product(name: "DI", package: "Macros"),
                "Utils",
                "DTO",
                .product(name: "Models", package: "Model"),
                "DataSource",
                "Swinject",
                "SwinjectAutoregistration",
            ]
        )
    ]
)
