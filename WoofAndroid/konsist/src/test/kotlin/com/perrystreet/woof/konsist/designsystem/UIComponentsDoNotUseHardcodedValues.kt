package com.perrystreet.woof.konsist.designsystem

import com.lemonappdev.konsist.api.ext.list.withAnnotationNamed
import com.lemonappdev.konsist.api.ext.list.withoutAnnotationNamed
import com.perrystreet.woof.konsist.Assertions.assertFalse
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class UIComponentsDoNotUseHardcodedValues : BehaviorSpec() {
    init {
        Given("A composable outside the primitives") {
            val composables = KonsistUtils.productionCode.functions()
                .withAnnotationNamed("Composable")
                .withoutAnnotationNamed("PreviewDevices")
                .filter { !it.path.contains("/_primitives/") }

            Then("It does not hardcode dp values") {
                composables.assertFalse(message = DpMessage) { HardcodedDpRegex.containsMatchIn(it.text) }
            }

            Then("It does not hardcode colors") {
                composables.assertFalse(message = ColorMessage) { HardcodedColorRegex.containsMatchIn(it.text) }
            }
        }
    }

    private companion object {
        private val HardcodedDpRegex = Regex("""[1-9]\d*\.dp\b""")
        private val HardcodedColorRegex = Regex("""Color\(0x""")

        private val DpMessage = LintRuleMessage(
            rule = "Composables never hardcode dp values.",
            why = "Spacing, sizing, and radii are tokens tuned in one place. A literal dp bypasses that and drifts between components.",
            howToFix = "Use Theme.padding.*, Theme.sizing.*, SpacingRoles.*, or SizingRoles.* instead of the literal.",
            badExample = "Modifier.padding(16.dp)",
            goodExample = "Modifier.padding(Theme.padding.elementRegular)",
        )

        private val ColorMessage = LintRuleMessage(
            rule = "Composables never hardcode colors.",
            why = "Colors come from the theme so light and dark variants stay in sync. A literal color ignores the active theme.",
            howToFix = "Add a semantic color to Colors and read it through Theme.colors or a *ColorRole.",
            badExample = "Text(color = Color(0xFFFF7A29))",
            goodExample = "AtomText(colorRole = TextColorRole.Primary)",
        )
    }
}
