plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ksp)
    id("java-test-fixtures")
}

dependencies {
    api(projects.dto)
    implementation(projects.utils)
    implementation(libs.rxjava)
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(platform(libs.koin.annotations.bom))
    implementation(libs.koin.annotations)
    ksp(platform(libs.koin.annotations.bom))
    ksp(libs.koin.ksp.compiler)

    testFixturesImplementation(projects.dto)
    testFixturesImplementation(libs.rxjava)
    testFixturesImplementation(platform(libs.koin.bom))
    testFixturesImplementation(libs.koin.core)
    testFixturesImplementation(libs.koin.test)
    testFixturesImplementation(platform(libs.koin.annotations.bom))
    testFixturesImplementation(libs.koin.annotations)
    kspTestFixtures(platform(libs.koin.annotations.bom))
    kspTestFixtures(libs.koin.ksp.compiler)
}
