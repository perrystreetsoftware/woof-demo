package com.perrystreet.woof.konsist.models

import com.lemonappdev.konsist.api.ext.list.properties
import com.perrystreet.woof.konsist.Assertions.assertFalse
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class DomainModelsAreImmutable : BehaviorSpec() {
    init {
        Given("A property of a domain model") {
            val properties = KonsistUtils.domainModelModule.classes().properties()

            Then("It is declared with val") {
                properties.assertFalse(message = Message) { it.hasVarModifier }
            }
        }
    }

    private companion object {
        private val Message = LintRuleMessage(
            rule = "Domain models only have val properties.",
            why = "Domain models flow through RxJava streams shared by several ViewModels. Mutable fields would let one consumer change what another is observing.",
            howToFix = "Use val and copy() to derive new instances.",
            badExample = "data class Dog(var name: String)",
            goodExample = "data class Dog(val name: String)",
        )
    }
}
