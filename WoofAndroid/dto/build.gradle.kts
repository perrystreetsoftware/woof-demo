plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ksp)
}

dependencies {
    api(libs.moshi)
    ksp(libs.moshi.codegen)
}
