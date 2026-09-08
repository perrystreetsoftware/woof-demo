plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.perrystreet.woof.di"
}

dependencies {
    implementation(projects.utils)
    implementation(projects.data.datasource)
    implementation(projects.data.repositories)
    implementation(projects.domain.usecase)
    implementation(projects.presentation.navigation)
    implementation(projects.presentation.grid)
    implementation(projects.presentation.profile)
    implementation(projects.presentation.favorites)
    implementation(projects.presentation.account)
    implementation(projects.presentation.home)

    implementation(libs.rxjava)
    implementation(libs.rxandroid)
    api(platform(libs.koin.bom))
    api(libs.koin.core)
    implementation(platform(libs.koin.annotations.bom))
    implementation(libs.koin.annotations)
    ksp(platform(libs.koin.annotations.bom))
    ksp(libs.koin.ksp.compiler)
}
