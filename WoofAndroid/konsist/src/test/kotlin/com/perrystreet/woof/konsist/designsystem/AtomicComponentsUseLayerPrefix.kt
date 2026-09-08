package com.perrystreet.woof.konsist.designsystem

import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.ext.list.modifierprovider.withPublicOrDefaultModifier
import com.lemonappdev.konsist.api.ext.list.withAnnotationNamed
import com.perrystreet.woof.konsist.Assertions.assertTrue
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class AtomicComponentsUseLayerPrefix : BehaviorSpec() {
    init {
        Given("A public composable in the atomic design system") {
            val composables = KonsistUtils.atomicDesignModule.functions().withAnnotationNamed("Composable").withPublicOrDefaultModifier().filter { it.isTopLevel }

            Then("Its name carries the prefix of its layer") {
                composables.assertTrue(message = Message) { composable ->
                    LayerPrefixes.any { (layer, prefix) -> composable.path.contains("/atomic/$layer/") && composable.hasPrefix(prefix) }
                }
            }
        }
    }

    private fun KoFunctionDeclaration.hasPrefix(prefix: String) =
        name.startsWith(prefix) || name.startsWith(prefix.replaceFirstChar { it.lowercase() })

    private companion object {
        private val LayerPrefixes = listOf("atoms" to "Atom", "molecules" to "Mol", "organisms" to "Org", "templates" to "Template")

        private val Message = LintRuleMessage(
            rule = "Public composables are prefixed by their layer: Atom*, Mol*, Org*, Template*.",
            why = "The prefix tells the caller which layer they are composing with, so a Screen can be checked for using only Template/Org/Mol/Atom at a glance.",
            howToFix = "Rename the composable with the prefix of the folder it lives in, or move it to the right layer.",
            badExample = "// in atomic/molecules/button/\n@Composable fun PrimaryButton(...)",
            goodExample = "// in atomic/molecules/button/\n@Composable fun MolButton(...)",
        )
    }
}
