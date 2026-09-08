package com.perrystreet.woof.konsist.viewmodel

import com.perrystreet.woof.konsist.Assertions.assertFalse
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class ViewModelsDoNotHaveInitializers : BehaviorSpec() {
    init {
        Given("A ViewModel") {
            val viewModels = KonsistUtils.viewModels

            Then("It does not declare an init block") {
                viewModels.assertFalse(message = Message) { it.hasInitBlocks() }
            }
        }
    }

    private companion object {
        private val Message = LintRuleMessage(
            rule = "ViewModels must not use init blocks.",
            why = """
                init runs while Koin constructs the ViewModel, before any view subscribes. Work started there
                cannot be tied to the screen lifecycle and makes tests set up state they never asked for.
            """.trimIndent(),
            howToFix = "Override onFirstAppear() for one-time setup or onEveryAppear() for recurring setup.",
            badExample = "init { loadDogs() }",
            goodExample = "override fun onFirstAppear() { loadDogs() }",
        )
    }
}
