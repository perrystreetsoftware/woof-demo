package com.perrystreet.woof.konsist.di

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withAnnotationNamed
import com.perrystreet.woof.konsist.Assertions.assertTrue
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class DIModulesAreRegistered : BehaviorSpec() {
    init {
        Given("A Koin module in production code") {
            val modules = KonsistUtils.productionCode.classes()
                .withAnnotationNamed("Module")
                .filter { !it.path.contains("/testFixtures/") }
            val registry = Konsist.scopeFromDirectory("di/src/main").files.first { it.name == "WoofKoinModules" }.text

            Then("It is registered in WoofKoinModules") {
                modules.assertTrue(message = Message) { registry.contains("${it.name}().module") }
            }
        }
    }

    private companion object {
        private val Message = LintRuleMessage(
            rule = "Every Koin @Module is registered in WoofKoinModules.",
            why = "An unregistered module compiles and passes its own view-model tests, which load the module directly, then crashes with NoDefinitionFoundException the first time the screen opens.",
            howToFix = "Add <Name>().module to the list in di/src/main/kotlin/com/perrystreet/woof/di/WoofKoinModules.kt and the feature module to di/build.gradle.kts.",
            badExample = """
                // presentation/favorites/di/FavoritesDIModule.kt
                @Module @ComponentScan("com.perrystreet.woof.presentation.favorites")
                class FavoritesDIModule
                // …and nothing in WoofKoinModules
            """.trimIndent(),
            goodExample = """
                val all: List<Module> = listOf(
                    FavoritesDIModule().module,
                )
            """.trimIndent(),
        )
    }
}
