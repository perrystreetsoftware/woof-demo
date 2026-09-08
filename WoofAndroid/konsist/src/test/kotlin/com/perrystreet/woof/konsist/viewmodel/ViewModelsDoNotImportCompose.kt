package com.perrystreet.woof.konsist.viewmodel

import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.perrystreet.woof.konsist.Assertions.assertFalse
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class ViewModelsDoNotImportCompose : BehaviorSpec() {
    init {
        Given("A ViewModel, UI model, or mapper file in a feature module") {
            val files = KonsistUtils.featureModules.files.withNameEndingWith("ViewModel", "UIModel", "UIModelMapper")

            Then("It does not import Compose, the design system, or resources") {
                files.assertFalse(message = Message) { file ->
                    file.hasImport { import ->
                        import.name.startsWith("androidx.compose") ||
                            import.name.startsWith("com.perrystreet.woof.designsystem") ||
                            import.name.startsWith("com.perrystreet.woof.resources")
                    }
                }
            }
        }
    }

    private companion object {
        private val Message = LintRuleMessage(
            rule = "ViewModels, UI models, and mappers must not import Compose, design system, or resource classes.",
            why = """
                Presentation logic must be testable on the JVM and reusable across views. Resource IDs and
                composables are view concerns and belong in screen-layer extensions.
            """.trimIndent(),
            howToFix = "Emit plain data in the UI model and map it to strings, icons, or colors in an extension under ui/extensions.",
            badExample = "data class ProfileToastUIModel(@StringRes val messageRes: Int)",
            goodExample = "sealed class ProfileToastUIModel { data class WoofSent(val name: String) }\n@Composable fun ProfileToastUIModel.text(): String = ...",
        )
    }
}
