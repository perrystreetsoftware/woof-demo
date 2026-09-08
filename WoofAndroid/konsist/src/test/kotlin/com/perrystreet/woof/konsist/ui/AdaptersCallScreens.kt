package com.perrystreet.woof.konsist.ui

import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.lemonappdev.konsist.api.ext.list.withoutName
import com.perrystreet.woof.konsist.Assertions.assertTrue
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.KonsistUtils.body
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class AdaptersCallScreens : BehaviorSpec() {
    init {
        Given("A feature Adapter composable") {
            val adapters = KonsistUtils.featureModules.functions().withNameEndingWith("Adapter").withoutName("ErrorAdapter")

            Then("It renders a Screen composable") {
                adapters.assertTrue(message = Message) { adapter -> ScreenCallRegex.containsMatchIn(adapter.body) }
            }
        }
    }

    private companion object {
        private val ScreenCallRegex = Regex("""\b[A-Z]\w*Screen\(""")

        private val Message = LintRuleMessage(
            rule = "Every Adapter delegates rendering to a Screen composable.",
            why = "The Adapter/Screen split only works if the Adapter is a thin bridge: collect state, bind callbacks, render the Screen.",
            howToFix = "Move layout code into a *Screen composable and call it from the Adapter.",
            badExample = "@Composable fun GridAdapter(viewModel: GridViewModel = koinViewModel()) { TemplateGrid(...) { ... } }",
            goodExample = "@Composable fun GridAdapter(viewModel: GridViewModel = koinViewModel()) { GridScreen(state = state, onCellTap = ...) }",
        )
    }
}
