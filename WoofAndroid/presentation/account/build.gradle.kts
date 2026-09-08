plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.perrystreet.woof.presentation.account"

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(projects.presentation.common)
    implementation(projects.presentation.navigation)
    implementation(projects.domain.usecase)
    implementation(projects.domain.model)
    implementation(projects.resources)
    implementation(projects.utils)

    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.koin.core.viewmodel)
    implementation(libs.koin.compose)
    implementation(platform(libs.koin.annotations.bom))
    implementation(libs.koin.annotations)
    ksp(platform(libs.koin.annotations.bom))
    ksp(libs.koin.ksp.compiler)

    testImplementation(projects.testutils)
    testRuntimeOnly(libs.junit.platform.launcher)
}
