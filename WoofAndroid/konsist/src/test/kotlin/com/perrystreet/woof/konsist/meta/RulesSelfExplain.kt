package com.perrystreet.woof.konsist.meta

import com.lemonappdev.konsist.api.ext.list.withoutName
import com.perrystreet.woof.konsist.Assertions.assertTrue
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class RulesSelfExplain : BehaviorSpec() {
    init {
        Given("A Konsist rule file") {
            val rules = KonsistUtils.konsistRulesFiles.withoutName("Assertions", "KonsistUtils", "LintRuleMessage")

            Then("It explains itself with a LintRuleMessage") {
                rules.assertTrue(message = Message) { it.hasImport { import -> import.name == "com.perrystreet.woof.konsist.LintRuleMessage" } }
            }
        }
    }

    private companion object {
        private val Message = LintRuleMessage(
            rule = "Every lint rule uses LintRuleMessage for its assertion message.",
            why = "A failing rule must explain why it exists and how to fix it, otherwise the next person works around it instead of fixing the code.",
            howToFix = "Replace the raw string with a LintRuleMessage(rule, why, howToFix, badExample, goodExample).",
            badExample = "classes.assertTrue(additionalMessage = \"Use @Factory\") { ... }",
            goodExample = "classes.assertTrue(message = Message) { ... }",
        )
    }
}
