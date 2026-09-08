// swift-tools-version: 5.10

import PackageDescription

let package = Package(
    name: "DataSource",
    platforms: [.iOS("18.0"), .macOS("15.0")],
    products: [
        .library(name: "DataSource", targets: ["DataSource"]),
        .library(name: "DataSourceFakes", targets: ["DataSourceFakes"]),
    ],
    dependencies: [
        .package(path: "../../Macros"),
        .package(path: "../../Utils"),
        .package(path: "../../DTO"),
        .package(url: "https://github.com/Swinject/Swinject.git", from: "2.9.1"),
        .package(url: "https://github.com/Swinject/SwinjectAutoregistration.git", from: "2.9.1"),
        .package(url: "https://github.com/pointfreeco/combine-schedulers", from: "1.0.3"),
    ],
    targets: [
        .target(
            name: "DataSource",
            dependencies: [
                .product(name: "DI", package: "Macros"),
                "Utils",
                "DTO",
                "Swinject",
                "SwinjectAutoregistration",
                .product(name: "CombineSchedulers", package: "combine-schedulers"),
            ]
        ),
        .target(
            name: "DataSourceFakes",
            dependencies: [
                "DataSource",
                .product(name: "DI", package: "Macros"),
                "Utils",
                "DTO",
                "Swinject",
                "SwinjectAutoregistration",
            ]
        ),
    ]
)
