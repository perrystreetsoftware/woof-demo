package com.perrystreet.woof.konsist.designsystem

import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.perrystreet.woof.konsist.Assertions.assertFalse
import com.perrystreet.woof.konsist.Assertions.assertTrue
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class DoNotUseRolesForFeatureData : BehaviorSpec() {
    init {
        Given("A Role type in an atomic component") {
            val roles = KonsistUtils.atomicDesignModule.classes()
                .withNameEndingWith("Role")
                .filter { it.isTopLevel && !it.path.contains("/_tokens/") }

            Then("It is an enum whose entries resolve Theme tokens") {
                roles.assertTrue(message = Message) { role -> role.hasEnumModifier && role.text.contains("Theme.") }
            }

            Then("It does not carry feature data such as drawables or strings") {
                roles.assertFalse(message = Message) { role ->
                    role.containingFile.hasImport { it.name == ResourcesImport }
                }
            }
        }
    }

    private companion object {
        private const val ResourcesImport = "com.perrystreet.woof.resources.R"

        private val Message = LintRuleMessage(
            rule = "Roles never carry feature data; they are enums whose entries resolve Theme tokens.",
            why = "A role is a predefined style the caller picks from a closed set (ButtonRole.Primary, IconColorRole.Recent). Icons and text are feature data; a role that carries them (IconButtonRole.Favorite) ties the design system to one feature.",
            howToFix = "Keep only Theme-backed styling in the role and pass feature data such as icons and content descriptions to the component as parameters.",
            badExample = """
                // atoms/button/roles/IconButtonRole.kt
                enum class IconButtonRole(@DrawableRes val iconRes: Int, @StringRes val contentDescriptionRes: Int) {
                    Favorite(iconRes = R.drawable.ic_star_outline, contentDescriptionRes = R.string.accessibility_add_favorite),
                }

                @Composable
                fun AtomIconButton(role: IconButtonRole, onTap: () -> Unit)
            """.trimIndent(),
            goodExample = """
                // atoms/icon/roles/IconColorRole.kt
                enum class IconColorRole {
                    Recent {
                        @Composable
                        override fun color(): Color = Theme.colors.recent
                    },
                    ;

                    @Composable
                    abstract fun color(): Color
                }

                @Composable
                fun AtomIconButton(@DrawableRes iconRes: Int, contentDescription: String, colorRole: IconColorRole, onTap: () -> Unit)
            """.trimIndent(),
        )
    }
}
