// swift-tools-version: 5.10

import PackageDescription

let package = Package(
    name: "Favorites",
    platforms: [.iOS("18.0"), .macOS("15.0")],
    products: [
        .library(name: "PresentationFavorites", targets: ["PresentationFavorites"])
    ],
    dependencies: [
        .package(path: "../../Macros"),
        .package(path: "../../Utils"),
        .package(path: "../../Domain/Model"),
        .package(path: "../../Domain/UseCase"),
        .package(path: "../../Data/DataSource"),
        .package(path: "../../Data/Repositories"),
        .package(path: "../../DesignSystem"),
        .package(path: "../../Resources"),
        .package(path: "../../TestUtils"),
        .package(path: "../Common"),
        .package(path: "../Navigation"),
        .package(url: "https://github.com/Swinject/Swinject.git", from: "2.9.1"),
        .package(url: "https://github.com/Swinject/SwinjectAutoregistration.git", from: "2.9.1"),
        .package(url: "https://github.com/Quick/Quick.git", from: "7.6.2"),
        .package(url: "https://github.com/Quick/Nimble.git", from: "13.7.1"),
    ],
    targets: [
        .target(
            name: "PresentationFavorites",
            dependencies: [
                .product(name: "DI", package: "Macros"),
                "Utils",
                .product(name: "Models", package: "Model"),
                "UseCase",
                "DesignSystem",
                "Resources",
                .product(name: "PresentationCommon", package: "Common"),
                .product(name: "PresentationNavigation", package: "Navigation"),
                "Swinject",
                "SwinjectAutoregistration",
            ]
        ),
        .testTarget(
            name: "PresentationFavoritesTests",
            dependencies: [
                "PresentationFavorites",
                "TestUtils",
                .product(name: "UtilsTestExtensions", package: "Utils"),
                .product(name: "DataSourceFakes", package: "DataSource"),
                "Repositories",
                .product(name: "Models", package: "Model"),
                .product(name: "PresentationNavigation", package: "Navigation"),
                "Swinject",
                "SwinjectAutoregistration",
                "Quick",
                "Nimble",
            ]
        ),
    ]
)
