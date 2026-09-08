plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ksp)
}

dependencies {
    api(projects.domain.model)
    implementation(projects.data.repositories)
    implementation(libs.rxjava)
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(platform(libs.koin.annotations.bom))
    implementation(libs.koin.annotations)
    ksp(platform(libs.koin.annotations.bom))
    ksp(libs.koin.ksp.compiler)
}
