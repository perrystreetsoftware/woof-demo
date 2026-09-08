package com.perrystreet.woof.konsist.designsystem

import com.perrystreet.woof.konsist.Assertions.assertFalse
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class TemplatesPadWithScreenRoles : BehaviorSpec() {
    init {
        Given("A file in the templates layer") {
            val files = KonsistUtils.atomicDesignModule.files.filter { it.hasPackage("..templates..") }

            Then("It pads with screen roles, never element roles") {
                files.assertFalse(message = Message) { it.text.contains(ElementPaddingUsage) }
            }
        }
    }

    private companion object {
        private const val ElementPaddingUsage = "PaddingRoles.Element"

        private val Message = LintRuleMessage(
            rule = "Templates pad with PaddingRoles.Screen, never PaddingRoles.Element.",
            why = """
                Padding applied by a Template is always the gutter between page content and the screen,
                so it belongs on the Screen scale. Element padding describes space inside a component,
                and a Template has no components of its own — it only places the slots it is given.
            """.trimIndent(),
            howToFix = """
                Use the Screen step that matches the gutter you want — ExtraCompact for a dense grid,
                Compact under a bar, Regular for page content, Expanded for an overlay drop. Gaps
                between items stay on SpacingRoles.
            """.trimIndent(),
            badExample = """
                contentPadding = PaddingValues(
                    start = PaddingRoles.Element.Compact.dp,
                    end = PaddingRoles.Element.Compact.dp,
                )
            """.trimIndent(),
            goodExample = """
                contentPadding = PaddingValues(
                    start = PaddingRoles.Screen.ExtraCompact.dp,
                    end = PaddingRoles.Screen.ExtraCompact.dp,
                )
            """.trimIndent(),
        )
    }
}
