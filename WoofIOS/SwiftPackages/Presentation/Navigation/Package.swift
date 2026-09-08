// swift-tools-version: 5.10

import PackageDescription

let package = Package(
    name: "Navigation",
    platforms: [.iOS("18.0"), .macOS("15.0")],
    products: [
        .library(name: "PresentationNavigation", targets: ["PresentationNavigation"])
    ],
    dependencies: [
        .package(path: "../../Macros"),
        .package(path: "../../Utils"),
        .package(url: "https://github.com/Swinject/Swinject.git", from: "2.9.1"),
        .package(url: "https://github.com/Swinject/SwinjectAutoregistration.git", from: "2.9.1"),
    ],
    targets: [
        .target(
            name: "PresentationNavigation",
            dependencies: [
                .product(name: "DI", package: "Macros"),
                "Utils",
                "Swinject",
                "SwinjectAutoregistration",
            ]
        )
    ]
)
