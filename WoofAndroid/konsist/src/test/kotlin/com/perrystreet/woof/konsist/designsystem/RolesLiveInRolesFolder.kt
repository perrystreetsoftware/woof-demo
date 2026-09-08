package com.perrystreet.woof.konsist.designsystem

import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.perrystreet.woof.konsist.Assertions.assertTrue
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class RolesLiveInRolesFolder : BehaviorSpec() {
    init {
        Given("A Role enum in an atomic component") {
            val roles = KonsistUtils.atomicDesignModule.classes().withNameEndingWith("Role").filter { !it.path.contains("/_tokens/") }

            Then("It lives in a roles package next to its component") {
                roles.assertTrue(message = Message) { it.resideInPackage("..roles") }
            }

            Then("It is an enum whose entries resolve to theme tokens") {
                roles.assertTrue(message = Message) { role -> role.hasEnumModifier && role.text.contains("Theme.") || role.text.contains("R.") }
            }
        }
    }

    private companion object {
        private val Message = LintRuleMessage(
            rule = "Component roles are enums under a roles package, and each entry resolves to a Theme token or resource.",
            why = """
                Roles are the vocabulary a component offers (TextFontRole.BodyP1, ButtonRole.Primary). Keeping them as
                enums next to their component limits the set of tokens a component can use and makes the API discoverable.
            """.trimIndent(),
            howToFix = "Move the role into <component>/roles/ and make every entry return a Theme.* token.",
            badExample = "// atoms/text/TextColorRole.kt\nclass TextColorRole(val color: Color)",
            goodExample = "// atoms/text/roles/TextColorRole.kt\nenum class TextColorRole { OnSurface { @Composable override fun color() = Theme.colors.onSurface } }",
        )
    }
}
