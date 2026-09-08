plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.perrystreet.woof.presentation.common"

    buildFeatures {
        compose = true
    }
}

dependencies {
    api(projects.designSystem)
    implementation(projects.resources)
    implementation(projects.utils)

    api(libs.rxjava)
    api(libs.rxkotlin)
    api(libs.androidx.lifecycle.viewmodel)
    api(platform(libs.androidx.compose.bom))
    api(libs.androidx.compose.runtime.rxjava3)
    implementation(libs.coil.compose)
}
