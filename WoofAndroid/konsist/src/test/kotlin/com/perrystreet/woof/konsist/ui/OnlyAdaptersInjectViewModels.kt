package com.perrystreet.woof.konsist.ui

import com.perrystreet.woof.konsist.Assertions.assertTrue
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class OnlyAdaptersInjectViewModels : BehaviorSpec() {
    init {
        Given("A composable that injects a ViewModel with koinViewModel") {
            val composables = KonsistUtils.composables.filter { it.text.contains("koinViewModel(") }

            Then("Its name ends with Adapter") {
                composables.assertTrue(message = Message) { it.name.endsWith("Adapter") }
            }
        }
    }

    private companion object {
        private val Message = LintRuleMessage(
            rule = "Only *Adapter composables inject ViewModels.",
            why = """
                Adapters bind ViewModels to Screens. Screens stay pure functions of state so they can be
                previewed and reasoned about without dependency injection.
            """.trimIndent(),
            howToFix = "Inject the ViewModel in an Adapter and pass state and callbacks down to the Screen.",
            badExample = "@Composable fun GridScreen(viewModel: GridViewModel = koinViewModel())",
            goodExample = "@Composable fun GridAdapter(viewModel: GridViewModel = koinViewModel()) { GridScreen(state = ...) }",
        )
    }
}
