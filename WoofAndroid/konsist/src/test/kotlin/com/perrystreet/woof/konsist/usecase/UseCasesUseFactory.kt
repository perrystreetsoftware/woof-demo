package com.perrystreet.woof.konsist.usecase

import com.perrystreet.woof.konsist.Assertions.assertTrue
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class UseCasesUseFactory : BehaviorSpec() {
    init {
        Given("A UseCase") {
            val useCases = KonsistUtils.useCases

            Then("It is annotated with @Factory") {
                useCases.assertTrue(message = Message) { it.hasAnnotationWithName("Factory") }
            }
        }
    }

    private companion object {
        private val Message = LintRuleMessage(
            rule = "UseCases must be annotated with @Factory.",
            why = "UseCases are stateless, so a new instance per injection is free and rules out accidental shared state.",
            howToFix = "Add @Factory to the UseCase class.",
            badExample = "@Single\nclass SendWoofUseCase(...)",
            goodExample = "@Factory\nclass SendWoofUseCase(...)",
        )
    }
}
