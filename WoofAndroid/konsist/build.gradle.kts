plugins {
    alias(libs.plugins.kotlin.jvm)
}

tasks.withType<Test>().configureEach {
    outputs.upToDateWhen { false }
}

dependencies {
    testImplementation(platform(libs.kotest.bom))
    testImplementation(libs.kotest.runner)
    testImplementation(libs.konsist)
    testRuntimeOnly(libs.junit.platform.launcher)
}
