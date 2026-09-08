package com.perrystreet.woof.konsist.ui

import com.lemonappdev.konsist.api.ext.list.withAnnotationNamed
import com.perrystreet.woof.konsist.Assertions.assertFalse
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class ViewLayerDoesNotUseIfStatements : BehaviorSpec() {
    init {
        Given("A composable in a presentation module") {
            val composables = KonsistUtils.presentationModules.functions().withAnnotationNamed("Composable")

            Then("It does not branch with if or ?.let") {
                composables.assertFalse(message = Message) { composable ->
                    IfRegex.containsMatchIn(composable.text) || composable.text.contains("?.let")
                }
            }
        }
    }

    private companion object {
        private val IfRegex = Regex("""\bif\s*\(""")

        private val Message = LintRuleMessage(
            rule = "Composables in presentation modules do not use if statements or ?.let.",
            why = """
                Branching in the view hides state decisions from the ViewModel and its tests. Sealed state plus
                exhaustive when, or an early guard, keeps every UI branch modelled and tested.
            """.trimIndent(),
            howToFix = "Model the decision in the ViewModel state and switch on it with when, or use guard(condition) { return } for nullability checks.",
            badExample = "if (state.dialog != null) { OrgAlertDialog(...) }",
            goodExample = "guard(dialog != null) { return }\nOrgAlertDialog(...)",
        )
    }
}
