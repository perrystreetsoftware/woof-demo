package com.perrystreet.woof.konsist.viewmodel

import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.perrystreet.woof.konsist.Assertions.assertFalse
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class ViewModelsDoNotDependOnRepositories : BehaviorSpec() {
    init {
        Given("A ViewModel file") {
            val files = KonsistUtils.featureModules.files.withNameEndingWith("ViewModel")

            Then("It does not import repositories or data sources") {
                files.assertFalse(message = Message) { file ->
                    file.hasImport { import ->
                        import.name.startsWith("com.perrystreet.woof.repositories") ||
                            import.name.startsWith("com.perrystreet.woof.datasource")
                    }
                }
            }
        }
    }

    private companion object {
        private val Message = LintRuleMessage(
            rule = "ViewModels must not import repositories or data sources.",
            why = """
                The flow is View -> ViewModel -> UseCase -> Repository -> DataSource. Skipping the use case
                layer spreads business rules across ViewModels and makes them untestable in isolation.
            """.trimIndent(),
            howToFix = "Wrap the repository call in a single-purpose UseCase and inject that instead.",
            badExample = "class GridViewModel(private val dogsRepository: DogsRepository)",
            goodExample = "class GridViewModel(private val getDogsFeedUseCase: GetDogsFeedUseCase)",
        )
    }
}
