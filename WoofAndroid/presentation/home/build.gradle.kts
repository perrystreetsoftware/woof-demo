plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.perrystreet.woof.presentation.home"

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(projects.presentation.common)
    implementation(projects.presentation.navigation)
    implementation(projects.presentation.grid)
    implementation(projects.presentation.favorites)
    implementation(projects.presentation.account)
    implementation(projects.resources)
    implementation(projects.utils)

    implementation(libs.androidx.activity.compose)
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
