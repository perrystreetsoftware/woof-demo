package com.perrystreet.woof.konsist.viewmodel

import com.lemonappdev.konsist.api.ext.list.functions
import com.lemonappdev.konsist.api.ext.list.modifierprovider.withPublicOrDefaultModifier
import com.lemonappdev.konsist.api.ext.list.withNameStartingWith
import com.perrystreet.woof.konsist.Assertions.assertFalse
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class ViewModelActionsUsePresentTense : BehaviorSpec() {
    init {
        Given("A public action on a ViewModel") {
            val actions = KonsistUtils.viewModels.functions().withPublicOrDefaultModifier().withNameStartingWith("on")

            Then("It is not named in the past tense") {
                actions.assertFalse(message = Message) { it.name.endsWith("ed") }
            }
        }
    }

    private companion object {
        private val Message = LintRuleMessage(
            rule = "ViewModel actions are named on<Something><Verb>, never in the past tense.",
            why = """
                Actions describe what the user does right now (onWoofTap), not what already happened.
                One naming style keeps adapters and tests predictable.
            """.trimIndent(),
            howToFix = "Rename onButtonTapped to onButtonTap, onTextChanged to onTextChange.",
            badExample = "fun onWoofTapped()",
            goodExample = "fun onWoofTap()",
        )
    }
}
