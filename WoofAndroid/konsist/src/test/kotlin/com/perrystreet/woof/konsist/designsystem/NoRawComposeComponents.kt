package com.perrystreet.woof.konsist.designsystem

import com.perrystreet.woof.konsist.Assertions.assertFalse
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class NoRawComposeComponents : BehaviorSpec() {
    init {
        Given("A molecule, organism or template file") {
            val files = KonsistUtils.atomicDesignModule.files
                .filter { file -> AboveAtoms.any { file.path.contains(it) } }

            Then("It uses the atoms instead of the raw Compose components they wrap") {
                files.assertFalse(message = Message) { file -> file.imports.any { RawComponent.matches(it.name) } }
            }
        }
    }

    private companion object {
        private val AboveAtoms = listOf("/molecules/", "/organisms/", "/templates/")
        private val RawComponent =
            Regex("""androidx\.compose\.(material3?|foundation(\.text)?)\.\w*(Text|Icon|Button|Divider|ProgressIndicator|TextField|Image)""")

        private val Message = LintRuleMessage(
            rule = "Molecules, organisms and templates build on atoms, never on the raw Compose components the atoms wrap.",
            why = "Each atom owns one element's tokens, states and accessibility. A molecule or organism that draws Text, Icon, an icon button or an image itself re-implements that atom and bypasses its tokens, so the two drift apart.",
            howToFix = "Replace the raw component with the atom that wraps it (AtomText, AtomIcon, AtomIconButton, AtomPainterImage, AtomTextField, AtomHorizontalDivider, AtomCircularProgressIndicator). If no atom covers it, add one. Layout (Row, Column, Box, Spacer) and Material containers such as Scaffold or AlertDialog stay as they are.",
            badExample = """
                import androidx.compose.material3.Text

                @Composable fun MolTitleSubtitle(title: String, subtitle: String) {
                    Column { Text(text = title); Text(text = subtitle) }
                }
            """.trimIndent(),
            goodExample = """
                @Composable fun MolTitleSubtitle(title: String, subtitle: String) {
                    Column {
                        AtomText(text = title, textFontRole = TextFontRole.SubheadP1)
                        AtomText(text = subtitle, textFontRole = TextFontRole.BodyP2)
                    }
                }
            """.trimIndent(),
        )
    }
}
