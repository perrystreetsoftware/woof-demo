package com.perrystreet.woof.konsist.usecase

import com.lemonappdev.konsist.api.ext.list.modifierprovider.withPublicOrDefaultModifier
import com.perrystreet.woof.konsist.Assertions.assertTrue
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class UseCasesExposeSingleInvoke : BehaviorSpec() {
    init {
        Given("A UseCase") {
            val useCases = KonsistUtils.useCases

            Then("Its only public function is operator fun invoke") {
                useCases.assertTrue(message = Message) { useCase ->
                    val publicFunctions = useCase.functions().withPublicOrDefaultModifier()
                    publicFunctions.size == 1 && publicFunctions.single().let { it.name == "invoke" && it.hasOperatorModifier }
                }
            }

            Then("It exposes no public properties") {
                useCases.assertTrue(message = Message) { useCase ->
                    useCase.properties().withPublicOrDefaultModifier().all { it.hasConstModifier }
                }
            }
        }
    }

    private companion object {
        private val Message = LintRuleMessage(
            rule = "UseCases expose exactly one operator fun invoke() and no public state.",
            why = """
                One class, one use case. A single invoke keeps the responsibility obvious and lets callers
                use the class like a function. Public state would turn it into a service.
            """.trimIndent(),
            howToFix = "Split additional public functions into their own UseCases; move state into a Repository.",
            badExample = "class DogsUseCase { fun load() {}; fun block() {} }",
            goodExample = "class LoadNextDogsPageUseCase { operator fun invoke(): Single<DogsPage> }",
        )
    }
}
