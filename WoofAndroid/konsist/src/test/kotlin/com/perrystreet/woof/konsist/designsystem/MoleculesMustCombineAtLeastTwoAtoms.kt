package com.perrystreet.woof.konsist.designsystem

import com.lemonappdev.konsist.api.ext.list.withAnnotationNamed
import com.lemonappdev.konsist.api.ext.list.withNameStartingWith
import com.lemonappdev.konsist.api.ext.list.withoutAnnotationNamed
import com.perrystreet.woof.konsist.Assertions.assertTrue
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class MoleculesMustCombineAtLeastTwoAtoms : BehaviorSpec() {
    init {
        Given("A molecule composable") {
            val atomNames = KonsistUtils.atomicDesignModule.functions()
                .withAnnotationNamed("Composable")
                .filter { it.path.contains("/atoms/") }
                .withNameStartingWith("Atom")
                .map { it.name }
                .toSet()

            val molecules = KonsistUtils.atomicDesignModule.functions()
                .withAnnotationNamed("Composable")
                .withoutAnnotationNamed("PreviewDevices")
                .filter { it.path.contains("/molecules/") }
                .withNameStartingWith("Mol")
                .filter { it.name !in Baseline }

            Then("It combines at least two atoms") {
                molecules.assertTrue(message = Message) { molecule ->
                    val source = molecule.text
                    val atomUsages = atomNames.sumOf { atom -> source.split("$atom(").size - 1 }
                    val iterations = IterationRegexes.sumOf { it.findAll(source).count() }
                    atomUsages + iterations >= 2
                }
            }
        }
    }

    private companion object {
        private val Baseline = setOf(
            "MolButton",
            "MolButtonCompact",
            "MolIconButton",
        )

        private val IterationRegexes = listOf(
            Regex("""\bitems\s*\("""),
            Regex("""\bitemsIndexed\s*\("""),
            Regex("""\bforEach\b"""),
            Regex("""\brepeat\s*\("""),
        )

        private val Message = LintRuleMessage(
            rule = "Molecules combine at least two atoms.",
            why = "A molecule exists to compose atoms. One atom in a wrapper adds nothing the atom did not already do.",
            howToFix = "Combine a second atom, or delete the molecule and call the atom directly. The same atom repeated counts.",
            badExample = "@Composable\nfun MolTitle() {\n    AtomText(text = title)\n}",
            goodExample = "@Composable\nfun MolTitleSubtitle() {\n    AtomText(text = title)\n    AtomText(text = subtitle)\n}",
        )
    }
}
