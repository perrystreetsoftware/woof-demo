package com.perrystreet.woof.konsist.designsystem

import com.perrystreet.woof.konsist.Assertions.assertFalse
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class DesignSystemDoesNotDependOnAppLayers : BehaviorSpec() {
    init {
        Given("A file in the design system") {
            val files = KonsistUtils.designSystemModule.files

            Then("It does not import domain models, use cases, repositories, presentation, or image loading libraries") {
                files.assertFalse(message = Message) { file ->
                    file.hasImport { import -> ForbiddenPrefixes.any { import.name.startsWith(it) } }
                }
            }
        }
    }

    private companion object {
        private val ForbiddenPrefixes = listOf(
            "com.perrystreet.woof.models",
            "com.perrystreet.woof.usecase",
            "com.perrystreet.woof.repositories",
            "com.perrystreet.woof.presentation",
            "coil3",
            "org.koin",
        )

        private val Message = LintRuleMessage(
            rule = "The design system knows nothing about domain models, UI models, DI, or image loaders.",
            why = """
                Components take plain data (strings, painters, roles). That is what makes them reusable across features
                and previewable without a running app. Image requests are built by the caller and passed in as a painter.
            """.trimIndent(),
            howToFix = "Accept primitive values, roles, or AsyncImageState instead of feature models, and build painters in the presentation layer.",
            badExample = "@Composable fun OrgPhotoCard(dog: Dog)",
            goodExample = "@Composable fun OrgPhotoCard(title: String, imageState: AsyncImageState, onTap: () -> Unit)",
        )
    }
}
