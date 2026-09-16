package com.perrystreet.woof.konsist.designsystem

import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.perrystreet.woof.konsist.Assertions.assertFalse
import com.perrystreet.woof.konsist.Assertions.assertTrue
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class StatesOwnTheirLook : BehaviorSpec() {
    init {
        Given("A State type in an atomic component") {
            val states = KonsistUtils.atomicDesignModule.classes()
                .withNameEndingWith("State")
                .filter { it.isTopLevel && !it.path.contains("/_tokens/") }

            Then("It is an enum or an immutable data class") {
                states.assertTrue(message = Message) { state ->
                    state.hasEnumModifier ||
                        (
                            state.hasDataModifier &&
                                state.hasAnnotationWithName("Immutable") &&
                                state.primaryConstructor?.parameters.orEmpty().none { it.isVar } &&
                                state.properties().none { it.isVar }
                            )
                }
            }

            Then("It resolves its look without caller input") {
                states.assertTrue(message = Message) { state -> state.functions().all { it.parameters.isEmpty() } }
            }

            Then("It never resolves drawables or strings") {
                states.assertFalse(message = Message) { state ->
                    state.containingFile.hasImport { it.name == ResourcesImport }
                }
            }
        }
    }

    private companion object {
        private const val ResourcesImport = "com.perrystreet.woof.resources.R"

        private val Message = LintRuleMessage(
            rule = "A State is what a component shows at one moment, and the component owns how it looks.",
            why = "Whether it is a mode (ButtonState.Loading, OverflowMenuState.Expanded) or data with a flag (AsyncImageState), the design system resolves the look from Theme tokens and roles, so callers describe what to show and never style it.",
            howToFix = "Make the type an enum (or an @Immutable data class with val properties), resolve its look from Theme tokens and roles, and pass icons and text as component parameters.",
            badExample = """
                // organisms/overflowmenu/OverflowMenuState.kt
                data class OverflowMenuState(var isExpanded: Boolean, @DrawableRes val iconRes: Int)
            """.trimIndent(),
            goodExample = """
                // organisms/overflowmenu/state/OverflowMenuState.kt
                enum class OverflowMenuState {
                    Default { override val colorRole = IconColorRole.OnScrim },
                    Expanded { override val colorRole = IconColorRole.Primary },
                    ;

                    abstract val colorRole: IconColorRole
                }
            """.trimIndent(),
        )
    }
}
