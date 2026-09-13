pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
    components {
        // koin-ksp-compiler generates code for koin-annotations 2.x, which koin-bom 4.2 would otherwise upgrade.
        withModule("io.insert-koin:koin-bom") {
            allVariants {
                withDependencyConstraints {
                    removeAll { it.name == "koin-annotations" }
                }
            }
        }
    }
}

rootProject.name = "WoofAndroid"

include(":app")
include(":design-system")
include(":resources")
include(":dto")
include(":data:datasource")
include(":data:repositories")
include(":domain:model")
include(":domain:usecase")
include(":di")
include(":presentation:common")
include(":presentation:navigation")
include(":presentation:grid")
include(":presentation:profile")
include(":presentation:favorites")
include(":presentation:account")
include(":presentation:home")
include(":utils")
include(":testutils")
include(":konsist")
