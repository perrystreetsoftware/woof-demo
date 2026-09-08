package com.perrystreet.woof.konsist.designsystem

import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.perrystreet.woof.konsist.Assertions.assertFalse
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class DesignSystemAtomicDependencies : BehaviorSpec() {
    init {
        Given("A file in the atomic design system") {
            val files = KonsistUtils.atomicDesignModule.files

            Then("Primitives depend on nothing else in the design system") {
                files.inLayer("_primitives").assertFalse(message = Message) { file ->
                    file.importsLayer("_tokens", "atoms", "molecules", "organisms", "templates", "theme")
                }
            }

            Then("Tokens depend only on primitives") {
                files.inLayer("_tokens").assertFalse(message = Message) { file ->
                    file.importsLayer("atoms", "molecules", "organisms", "templates", "theme")
                }
            }

            Then("Atoms depend only on tokens and the theme") {
                files.inLayer("atoms").assertFalse(message = Message) { file ->
                    file.importsLayer("_primitives", "molecules", "organisms", "templates")
                }
            }

            Then("Molecules depend only on atoms, tokens, and the theme") {
                files.inLayer("molecules").assertFalse(message = Message) { file ->
                    file.importsLayer("_primitives", "organisms", "templates") || file.importsOtherMolecules()
                }
            }

            Then("Organisms depend only on molecules, atoms, tokens, the theme, and other organisms") {
                files.inLayer("organisms").assertFalse(message = Message) { file ->
                    file.importsLayer("_primitives", "templates")
                }
            }

            Then("Templates depend only on organisms, molecules, atoms, tokens, and the theme") {
                files.inLayer("templates").assertFalse(message = Message) { file ->
                    file.importsLayer("_primitives")
                }
            }
        }
    }

    private fun List<KoFileDeclaration>.inLayer(layer: String) = filter { it.path.contains("/atomic/$layer/") }

    private fun KoFileDeclaration.importsLayer(vararg layers: String) =
        hasImport { import -> layers.any { layer -> import.name.contains(".atomic.$layer.") } }

    private fun KoFileDeclaration.importsOtherMolecules(): Boolean {
        val ownPackage = packagee?.name.orEmpty()
        return hasImport { import ->
            import.name.contains(".atomic.molecules.") &&
                !import.name.startsWith(ownPackage) &&
                !import.name.contains(".roles.") &&
                !import.name.contains(".state.")
        }
    }

    private companion object {
        private val Message = LintRuleMessage(
            rule = "Atomic layers only depend on the layers below them: _primitives -> _tokens -> atoms -> molecules -> organisms -> templates.",
            why = """
                Strict layering is what makes the design system composable: an atom can be reused anywhere because it
                depends on nothing above it, and a molecule never hides a whole organism inside.
            """.trimIndent(),
            howToFix = "Move shared code down (to atoms or tokens) or composition up (to organisms or templates) so imports follow the dependency flow.",
            badExample = "// in atoms/text/AtomText.kt\nimport com.perrystreet.woof.designsystem.atomic.molecules.button.MolButton",
            goodExample = "// in molecules/button/MolButton.kt\nimport com.perrystreet.woof.designsystem.atomic.atoms.text.AtomText",
        )
    }
}
