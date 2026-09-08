package com.perrystreet.woof.konsist.ui

import com.lemonappdev.konsist.api.ext.list.withAnnotationNamed
import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.perrystreet.woof.konsist.Assertions.assertTrue
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class ScreensHaveDevicePreviews : BehaviorSpec() {
    init {
        Given("A Screen file") {
            val files = KonsistUtils.featureModules.files.withNameEndingWith("Screen")

            Then("It declares at least one @PreviewDevices preview") {
                files.assertTrue(message = Message) { file -> file.functions().withAnnotationNamed("PreviewDevices").isNotEmpty() }
            }
        }

        Given("A @PreviewDevices preview") {
            val previews = KonsistUtils.productionCode.functions().withAnnotationNamed("PreviewDevices")

            Then("It is private and takes the theme from ThemeProvider") {
                previews.assertTrue(message = Message) { preview ->
                    preview.hasPrivateModifier &&
                        preview.parameters.any { it.hasAnnotation { annotation -> annotation.text.contains("ThemeProvider") } }
                }
            }
        }
    }

    private companion object {
        private val Message = LintRuleMessage(
            rule = "Every Screen has a private @PreviewDevices preview that takes its theme from ThemeProvider.",
            why = "Previews are how a screen is reviewed in every theme and form factor without running the app. ThemeProvider renders light and dark in one shot.",
            howToFix = "Add a private preview annotated with @PreviewDevices and a @PreviewParameter(ThemeProvider::class) theme parameter.",
            badExample = "@Preview @Composable fun GridScreenPreview() { GridScreen(...) }",
            goodExample = "@PreviewDevices @Composable private fun GridScreenPreview(@PreviewParameter(ThemeProvider::class) theme: ITheme) { ThemedScreenPreview(theme) { GridScreen(...) } }",
        )
    }
}
