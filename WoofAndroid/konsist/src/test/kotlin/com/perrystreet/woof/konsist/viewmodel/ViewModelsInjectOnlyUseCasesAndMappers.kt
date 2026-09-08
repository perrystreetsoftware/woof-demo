package com.perrystreet.woof.konsist.viewmodel

import com.perrystreet.woof.konsist.Assertions.assertTrue
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class ViewModelsInjectOnlyUseCasesAndMappers : BehaviorSpec() {
    init {
        Given("A ViewModel constructor") {
            val parameters = KonsistUtils.viewModels.flatMap { it.primaryConstructor?.parameters.orEmpty() }

            Then("Every dependency is a UseCase, a Mapper, the INavigator, or an injected parameter") {
                parameters.assertTrue(message = Message) { parameter ->
                    parameter.type.name.endsWith("UseCase") ||
                        parameter.type.name.endsWith("Mapper") ||
                        parameter.type.name == "INavigator" ||
                        parameter.hasAnnotationWithName("InjectedParam")
                }
            }
        }
    }

    private companion object {
        private val Message = LintRuleMessage(
            rule = "ViewModels may only inject UseCases, Mappers, INavigator (for state-driven navigation), and @InjectedParam values.",
            why = """
                ViewModels orchestrate use cases and shape their output for the UI. Any other dependency
                (repositories, data sources, framework classes) belongs to a lower layer.
            """.trimIndent(),
            howToFix = "Move the work into a UseCase or a Mapper and inject that.",
            badExample = "class ProfileWoofViewModel(private val woofsRepository: WoofsRepository)",
            goodExample = "class ProfileWoofViewModel(@InjectedParam private val dog: Dog, private val sendWoofUseCase: SendWoofUseCase)",
        )
    }
}
