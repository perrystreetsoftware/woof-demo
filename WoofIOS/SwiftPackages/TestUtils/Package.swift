// swift-tools-version: 5.10

import PackageDescription

let package = Package(
    name: "TestUtils",
    platforms: [.iOS("18.0"), .macOS("15.0")],
    products: [
        .library(name: "TestUtils", targets: ["TestUtils"])
    ],
    dependencies: [
        .package(path: "../Utils"),
        .package(path: "../Data/DataSource"),
        .package(path: "../Data/Repositories"),
        .package(path: "../Domain/UseCase"),
        .package(url: "https://github.com/Swinject/Swinject.git", from: "2.9.1"),
        .package(url: "https://github.com/Swinject/SwinjectAutoregistration.git", from: "2.9.1"),
        .package(url: "https://github.com/pointfreeco/combine-schedulers", from: "1.0.3"),
        .package(url: "https://github.com/groue/CombineExpectations.git", from: "0.10.0"),
    ],
    targets: [
        .target(
            name: "TestUtils",
            dependencies: [
                "Utils",
                .product(name: "UtilsTestExtensions", package: "Utils"),
                "DataSource",
                .product(name: "DataSourceFakes", package: "DataSource"),
                "Repositories",
                "UseCase",
                "Swinject",
                "SwinjectAutoregistration",
                .product(name: "CombineSchedulers", package: "combine-schedulers"),
                "CombineExpectations",
            ]
        )
    ]
)
