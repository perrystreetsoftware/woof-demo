package com.perrystreet.woof.konsist.viewmodel

import com.perrystreet.woof.konsist.Assertions.assertTrue
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class UsesKoinViewModelAnnotation : BehaviorSpec() {
    init {
        Given("A ViewModel in a feature module") {
            val viewModels = KonsistUtils.viewModels

            Then("It is annotated with @KoinViewModel") {
                viewModels.assertTrue(message = Message) { it.hasAnnotationWithName("KoinViewModel") }
            }
        }
    }

    private companion object {
        private val Message = LintRuleMessage(
            rule = "ViewModels must be annotated with @KoinViewModel.",
            why = """
                The Koin KSP processor scans @KoinViewModel and generates the registration, so the
                DI graph stays uniform and nobody hand-edits modules when a ViewModel gains a dependency.
            """.trimIndent(),
            howToFix = "Add @KoinViewModel to the ViewModel class.",
            badExample = "class GridViewModel(...) : StateProducingViewModel<State>(State.Loading)",
            goodExample = "@KoinViewModel\nclass GridViewModel(...) : StateProducingViewModel<State>(State.Loading)",
        )
    }
}
