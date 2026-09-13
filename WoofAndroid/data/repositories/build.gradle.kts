plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ksp)
}

dependencies {
    api(projects.domain.model)
    implementation(projects.data.datasource)
    implementation(projects.dto)
    implementation(libs.rxjava)
    implementation(libs.rxkotlin)
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.koin.annotations)
    ksp(libs.koin.ksp.compiler)
}
