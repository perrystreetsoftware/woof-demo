package com.perrystreet.woof.konsist.designsystem

import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.perrystreet.woof.konsist.Assertions.assertTrue
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class StatesLiveInStateFolder : BehaviorSpec() {
    init {
        Given("A State type in an atomic component") {
            val states = KonsistUtils.atomicDesignModule.classes()
                .withNameEndingWith("State")
                .filter { it.isTopLevel && !it.path.contains("/_tokens/") }

            Then("It lives in a state package next to its component") {
                states.assertTrue(message = Message) { it.resideInPackage("..state") }
            }
        }
    }

    private companion object {
        private val Message = LintRuleMessage(
            rule = "Component states live in a state package next to their component.",
            why = "A state is part of a component's API (ButtonState, OverflowMenuState); keeping it next to the component makes the API discoverable.",
            howToFix = "Move the state into <component>/state/.",
            badExample = "// molecules/button/ButtonState.kt\nenum class ButtonState { ... }",
            goodExample = "// molecules/button/state/ButtonState.kt\nenum class ButtonState { ... }",
        )
    }
}
