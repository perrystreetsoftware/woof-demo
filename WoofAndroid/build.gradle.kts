import com.android.build.api.dsl.LibraryExtension
import com.google.devtools.ksp.gradle.KspExtension
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.ksp) apply false
}

subprojects {
    tasks.withType<Test>().configureEach {
        testLogging {
            listOf(lifecycle, quiet).forEach { level ->
                level.events(TestLogEvent.FAILED)
                level.exceptionFormat = TestExceptionFormat.FULL
                level.showStackTraces = false
                level.showCauses = false
            }
        }
    }

    plugins.withId("java-base") {
        extensions.configure<JavaPluginExtension> {
            toolchain.languageVersion.set(JavaLanguageVersion.of(libs.versions.java.get().toInt()))
        }
    }

    plugins.withId("org.jetbrains.kotlin.jvm") {
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
            testOptions {
                unitTests.all { it.useJUnitPlatform() }
            }
        }
    }

    plugins.withId("com.google.devtools.ksp") {
        extensions.configure<KspExtension> {
            arg("KOIN_CONFIG_CHECK", "false")
            arg("KOIN_DEFAULT_MODULE", "false")
        }
    }
}

tasks.register("runUnitTests") {
    dependsOn(
        subprojects
            .filter { it.path.startsWith(":presentation:") }
            .map { "${it.path}:testDebugUnitTest" },
    )
}

tasks.register("runKonsistTests") {
    dependsOn(":konsist:test")
}
