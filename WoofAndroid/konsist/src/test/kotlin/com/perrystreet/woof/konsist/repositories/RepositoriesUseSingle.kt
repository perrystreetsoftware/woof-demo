package com.perrystreet.woof.konsist.repositories

import com.perrystreet.woof.konsist.Assertions.assertTrue
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class RepositoriesUseSingle : BehaviorSpec() {
    init {
        Given("A Repository") {
            val repositories = KonsistUtils.repositories

            Then("It is annotated with @Single") {
                repositories.assertTrue(message = Message) { it.hasAnnotationWithName("Single") }
            }
        }
    }

    private companion object {
        private val Message = LintRuleMessage(
            rule = "Repositories must be annotated with @Single.",
            why = "Repositories hold the in-memory state every feature observes. A second instance would mean a second, out-of-sync source of truth.",
            howToFix = "Add @Single to the Repository class.",
            badExample = "@Factory\nclass DogsRepository(...)",
            goodExample = "@Single\nclass DogsRepository(...)",
        )
    }
}
