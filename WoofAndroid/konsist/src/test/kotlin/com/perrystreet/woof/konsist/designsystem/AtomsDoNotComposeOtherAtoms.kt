package com.perrystreet.woof.konsist.designsystem

import com.lemonappdev.konsist.api.ext.list.withAnnotationNamed
import com.perrystreet.woof.konsist.Assertions.assertFalse
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.KonsistUtils.body
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class AtomsDoNotComposeOtherAtoms : BehaviorSpec() {
    init {
        Given("An atom composable") {
            val atoms = KonsistUtils.atomicDesignModule.functions().withAnnotationNamed("Composable").filter { it.path.contains("/atomic/atoms/") }

            Then("It does not call another atom") {
                atoms.assertFalse(message = Message) { atom -> AtomCallRegex.containsMatchIn(atom.body) }
            }
        }
    }

    private companion object {
        private val AtomCallRegex = Regex("""\bAtom[A-Z]\w*\(""")

        private val Message = LintRuleMessage(
            rule = "Atoms are built from Compose primitives only; they never call other atoms.",
            why = "The moment two atoms combine, the result is a molecule. Keeping atoms leaf-level keeps the hierarchy honest and the dependency graph flat.",
            howToFix = "Promote the composition to a Mol* component in atomic/molecules.",
            badExample = "@Composable fun AtomLabeledIcon() { AtomIcon(...); AtomText(...) }",
            goodExample = "@Composable fun MolLabeledIcon() { AtomIcon(...); AtomText(...) }",
        )
    }
}
