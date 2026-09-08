import com.android.build.api.dsl.LibraryExtension
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.ksp) apply false
}

subprojects {
    plugins.withId("org.jetbrains.kotlin.jvm") {
        extensions.configure<JavaPluginExtension> {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }
        extensions.configure<KotlinJvmProjectExtension> {
            compilerOptions.jvmTarget.set(JvmTarget.JVM_17)
        }
        tasks.withType<Test>().configureEach {
            useJUnitPlatform()
        }
    }

    plugins.withId("com.android.library") {
        extensions.configure<LibraryExtension> {
            compileSdk {
                version = release(libs.versions.android.sdk.compile.get().toInt())
            }
            defaultConfig {
                minSdk = libs.versions.android.sdk.min.get().toInt()
            }
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }
            testOptions {
                unitTests.all { it.useJUnitPlatform() }
            }
        }
    }

    plugins.withId("com.google.devtools.ksp") {
        extensions.configure<com.google.devtools.ksp.gradle.KspExtension> {
            arg("KOIN_CONFIG_CHECK", "false")
            arg("KOIN_DEFAULT_MODULE", "false")
        }
    }
}

tasks.register("runUnitTests") {
    dependsOn(
        ":presentation:grid:testDebugUnitTest",
        ":presentation:profile:testDebugUnitTest",
        ":presentation:favorites:testDebugUnitTest",
        ":presentation:account:testDebugUnitTest",
        ":presentation:home:testDebugUnitTest",
    )
}

tasks.register("runKonsistTests") {
    dependsOn(":konsist:test")
}
