package com.perrystreet.woof.konsist.designsystem

import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.perrystreet.woof.konsist.Assertions.assertTrue
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class RolesLiveInRolesFolder : BehaviorSpec() {
    init {
        Given("A Role type in an atomic component") {
            val roles = KonsistUtils.atomicDesignModule.classes()
                .withNameEndingWith("Role")
                .filter { it.isTopLevel && !it.path.contains("/_tokens/") }

            Then("It lives in a roles package next to its component") {
                roles.assertTrue(message = Message) { it.resideInPackage("..roles") }
            }
        }
    }

    private companion object {
        private val Message = LintRuleMessage(
            rule = "Component roles live in a roles package next to their component.",
            why = "Roles are the vocabulary a component offers (TextFontRole.BodyP1, ButtonRole.Primary); keeping them next to the component makes the API discoverable.",
            howToFix = "Move the role into <component>/roles/.",
            badExample = "// atoms/text/TextColorRole.kt\nenum class TextColorRole { ... }",
            goodExample = "// atoms/text/roles/TextColorRole.kt\nenum class TextColorRole { ... }",
        )
    }
}
