package com.perrystreet.woof.konsist.ui

import com.perrystreet.woof.konsist.Assertions.assertTrue
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.KonsistUtils.body
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class ScreensUseTemplates : BehaviorSpec() {
    init {
        Given("A Screen composable") {
            val screens = KonsistUtils.screenComposables

            Then("It renders a Template or delegates to another Screen") {
                screens.assertTrue(message = Message) { screen -> TemplateOrScreenCallRegex.containsMatchIn(screen.body) }
            }
        }
    }

    private companion object {
        private val TemplateOrScreenCallRegex = Regex("""\b(Template\w+|[A-Z]\w*Screen)\(""")

        private val Message = LintRuleMessage(
            rule = "Screen composables use a Template* as their root layout.",
            why = "Templates own page structure (top bar, insets, paddings, scrolling). Screens only fill their slots, so every screen shares the same skeleton.",
            howToFix = "Wrap the screen content in an existing Template, or add a new Template to the design system.",
            badExample = "@Composable fun GridScreen(state: State) { Scaffold { LazyVerticalGrid { ... } } }",
            goodExample = "@Composable fun GridScreen(state: State) { TemplateGrid(topBar = { OrgNavigationHeaderBranded() }) { ... } }",
        )
    }
}
