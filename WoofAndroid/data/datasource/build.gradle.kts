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
    implementation(libs.koin.annotations)
    ksp(libs.koin.ksp.compiler)

    testFixturesImplementation(projects.dto)
    testFixturesImplementation(libs.rxjava)
    testFixturesImplementation(platform(libs.koin.bom))
    testFixturesImplementation(libs.koin.core)
    testFixturesImplementation(libs.koin.test)
    testFixturesImplementation(libs.koin.annotations)
    kspTestFixtures(libs.koin.ksp.compiler)
}
