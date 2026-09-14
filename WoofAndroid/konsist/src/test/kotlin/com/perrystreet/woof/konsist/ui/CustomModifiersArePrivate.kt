package com.perrystreet.woof.konsist.ui

import com.lemonappdev.konsist.api.ext.list.modifierprovider.withPublicOrDefaultModifier
import com.perrystreet.woof.konsist.Assertions.assertEmpty
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class CustomModifiersArePrivate : BehaviorSpec() {
    init {
        Given("A custom Modifier extension") {
            val modifiers = KonsistUtils.productionCode.functions(includeNested = true)
                .filter { it.receiverType?.name == "Modifier" }
                .withPublicOrDefaultModifier()
                .filter { it.name !in Baseline }

            Then("It is private") {
                modifiers.assertEmpty(message = Message)
            }
        }
    }

    private companion object {
        private val Baseline = setOf(
            "sectionContainer",
        )

        private val Message = LintRuleMessage(
            rule = "Custom Modifier extensions are private.",
            why = "A public modifier spreads styling outside the component that owns it, so callers restyle one-off instead of picking a variant.",
            howToFix = "Make the modifier private to its component. If several components need it, move them into that file as overloads of one component.",
            badExample = """
                fun Modifier.atomPlaceholderShimmer(): Modifier = ...
            """.trimIndent(),
            goodExample = """
                @Composable
                fun AtomPlaceholder(role: TextPlaceholderRole) {
                    Box(modifier = Modifier.placeholderShimmer(role.radius()))
                }

                @Composable
                fun AtomPlaceholder(role: ShapePlaceholderRole) {
                    Box(modifier = Modifier.placeholderShimmer(role.radius()))
                }

                private fun Modifier.placeholderShimmer(radius: Dp): Modifier = ...
            """.trimIndent(),
        )
    }
}
