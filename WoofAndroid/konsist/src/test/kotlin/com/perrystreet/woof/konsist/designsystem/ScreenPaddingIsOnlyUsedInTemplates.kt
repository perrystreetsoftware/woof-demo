package com.perrystreet.woof.konsist.designsystem

import com.perrystreet.woof.konsist.Assertions.assertTrue
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class ScreenPaddingIsOnlyUsedInTemplates : BehaviorSpec() {
    init {
        Given("A production file that applies a screen padding role") {
            val files =
                KonsistUtils.productionCode.files
                    .filter { it.text.contains(ScreenPaddingUsage) }

            Then("It lives in a templates package") {
                files.assertTrue(message = Message) { it.hasPackage("..templates..") }
            }
        }
    }

    private companion object {
        private const val ScreenPaddingUsage = "PaddingRoles.Screen"

        private val Message =
            LintRuleMessage(
                rule = "Screen padding roles may only be applied by Templates.",
                why =
                    """
                    PaddingRoles.Screen describes the gutter between page content and the screen edge.
                    Templates are the one layer that owns page level layout, so if an Atom, Molecule or
                    Organism also applies it the gutter is added twice and drifts between screens.
                    """.trimIndent(),
                howToFix =
                    """
                    Let the Template apply the screen padding through its content padding, and use
                    PaddingRoles.Element or SpacingRoles inside the component.
                    """.trimIndent(),
                badExample =
                    """
                    @Composable
                    fun OrgFooList() {
                        Column(
                            modifier = Modifier.padding(horizontal = PaddingRoles.Screen.Regular.dp),
                        ) {
                            FooContent()
                        }
                    }
                    """.trimIndent(),
                goodExample =
                    """
                    @Composable
                    fun TemplateScrollableContent(content: LazyListScope.() -> Unit) {
                        LazyColumn(
                            contentPadding = PaddingValues(
                                start = PaddingRoles.Screen.Regular.dp,
                                end = PaddingRoles.Screen.Regular.dp,
                            ),
                            content = content,
                        )
                    }
                    """.trimIndent(),
            )
    }
}
