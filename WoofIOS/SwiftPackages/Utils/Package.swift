// swift-tools-version: 5.10

import PackageDescription

let package = Package(
    name: "Utils",
    platforms: [.iOS("18.0"), .macOS("15.0")],
    products: [
        .library(name: "Utils", targets: ["Utils"]),
        .library(name: "UtilsTestExtensions", targets: ["UtilsTestExtensions"]),
    ],
    dependencies: [
        .package(path: "../Macros"),
        .package(url: "https://github.com/Swinject/Swinject.git", from: "2.9.1"),
        .package(url: "https://github.com/Swinject/SwinjectAutoregistration.git", from: "2.9.1"),
        .package(url: "https://github.com/pointfreeco/combine-schedulers", from: "1.0.3"),
        .package(url: "https://github.com/Quick/Quick.git", from: "7.6.2"),
        .package(url: "https://github.com/Quick/Nimble.git", from: "13.7.1"),
        .package(url: "https://github.com/groue/CombineExpectations.git", from: "0.10.0"),
    ],
    targets: [
        .target(
            name: "Utils",
            dependencies: [
                .product(name: "DI", package: "Macros"),
                "Swinject",
                "SwinjectAutoregistration",
                .product(name: "CombineSchedulers", package: "combine-schedulers"),
            ]
        ),
        .target(
            name: "UtilsTestExtensions",
            dependencies: [
                "Quick",
                "Nimble",
                "CombineExpectations",
            ]
        ),
    ]
)
