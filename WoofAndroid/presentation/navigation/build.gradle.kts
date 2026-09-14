plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.perrystreet.woof.presentation.navigation"

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(projects.designSystem)

    api(platform(libs.androidx.compose.bom))
    api(libs.androidx.navigation3.runtime)
    api(libs.androidx.navigation3.ui)
    implementation(libs.androidx.lifecycle.viewmodel.navigation3)
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.koin.compose)
    implementation(platform(libs.koin.annotations.bom))
    implementation(libs.koin.annotations)
    ksp(platform(libs.koin.annotations.bom))
    ksp(libs.koin.ksp.compiler)
}
