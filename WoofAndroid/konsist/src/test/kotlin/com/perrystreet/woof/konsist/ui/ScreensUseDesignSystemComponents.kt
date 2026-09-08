package com.perrystreet.woof.konsist.ui

import com.perrystreet.woof.konsist.Assertions.assertFalse
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class ScreensUseDesignSystemComponents : BehaviorSpec() {
    init {
        Given("A file in a feature ui package") {
            val files = KonsistUtils.featureModules.files.filter { it.hasPackage("com.perrystreet.woof.presentation..ui..") }

            Then("It does not import raw Compose components, Modifier, or design tokens") {
                files.assertFalse(message = Message) { file ->
                    file.hasImport { import -> ForbiddenImportPrefixes.any { import.name.startsWith(it) } }
                }
            }
        }
    }

    private companion object {
        private val ForbiddenImportPrefixes = listOf(
            "androidx.compose.material3",
            "androidx.compose.foundation.layout",
            "androidx.compose.foundation.Image",
            "androidx.compose.foundation.background",
            "androidx.compose.ui.Modifier",
            "com.perrystreet.woof.designsystem.theme.Theme",
            "com.perrystreet.woof.designsystem.atomic._tokens",
            "com.perrystreet.woof.designsystem.atomic._primitives",
        )

        private val Message = LintRuleMessage(
            rule = "Feature UI only composes Template*, Org*, Mol*, and Atom* components and never touches Modifier or design tokens.",
            why = """
                If a screen reaches for Text, Box, Modifier.padding, or Theme.colors, styling decisions leak out of
                the design system and drift between features. The design system is the only place tokens are resolved.
            """.trimIndent(),
            howToFix = "Use the matching atomic component, or add a new one (or a new role) to the design system.",
            badExample = "Text(text = dog.name, color = Theme.colors.onSurface, modifier = Modifier.padding(8.dp))",
            goodExample = "AtomText(text = dog.name, textFontRole = TextFontRole.SubheadP2)",
        )
    }
}
