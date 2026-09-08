package com.perrystreet.woof.konsist.models

import com.lemonappdev.konsist.api.ext.list.modifierprovider.withSealedModifier
import com.lemonappdev.konsist.api.ext.list.objects
import com.perrystreet.woof.konsist.Assertions.assertTrue
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class SealedObjectSubclassDataModifier : BehaviorSpec() {
    init {
        Given("An object nested in a sealed class") {
            val objects = KonsistUtils.productionCode.classes().withSealedModifier().objects()

            Then("It is a data object") {
                objects.assertTrue(message = Message) { it.hasCompanionModifier || it.hasDataModifier }
            }
        }
    }

    private companion object {
        private val Message = LintRuleMessage(
            rule = "Objects inside sealed classes must be data objects.",
            why = "Sealed hierarchies mix objects and data classes. data object gives the objects a readable toString and equality that matches their siblings, which keeps test assertions symmetric.",
            howToFix = "Add the data modifier to the object.",
            badExample = "sealed class State { object Loading : State() }",
            goodExample = "sealed class State { data object Loading : State() }",
        )
    }
}
