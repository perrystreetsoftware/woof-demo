plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    api(projects.utils)
    api(projects.domain.usecase)
    api(projects.data.repositories)
    api(testFixtures(projects.data.datasource))
    api(libs.rxjava)
    api(platform(libs.koin.bom))
    api(libs.koin.core)
    api(libs.koin.test)
    api(platform(libs.kotest.bom))
    api(libs.kotest.runner)
    api(libs.kluent)
}
