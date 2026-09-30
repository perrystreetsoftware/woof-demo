package com.perrystreet.woof.konsist.ui

import com.lemonappdev.konsist.api.provider.KoTextProvider
import com.perrystreet.woof.konsist.Assertions.assertFalse
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class PresentationTextComesFromResources : BehaviorSpec() {
    init {
        Given("A function or property in a presentation module") {
            val scope = KonsistUtils.presentationModules.slice { !it.path.contains("/preview/") }
            val functions = scope.functions().filter { !it.hasAnnotationWithName("PreviewDevices") }
            val properties = scope.properties().filter { !it.hasConstModifier }

            Then("It does not hardcode text") {
                (functions + properties).assertFalse(message = Message) { it.hasHardcodedText() }
            }
        }
    }

    private companion object {
        private val KeyArgument = Regex("""\bkey\s*=\s*"[^"]*"""")
        private val StringLiteral = Regex(""""((?:[^"\\]|\\.)*)"""")
        private val Template = Regex("""\$\{[^}]*}|\$[A-Za-z_][A-Za-z0-9_]*""")
        private val Letter = Regex("[A-Za-z]")

        fun KoTextProvider.hasHardcodedText(): Boolean =
            StringLiteral.findAll(text.replace(KeyArgument, ""))
                .any { literal -> Letter.containsMatchIn(literal.groupValues[1].replace(Template, "")) }

        private val Message = LintRuleMessage(
            rule = "Text the user sees comes from string resources, never from literals in presentation code.",
            why = "Resources are where text is translated, pluralised and reviewed in one place. A literal can't be localised, and any value it formats belongs in the mapper or view model, where it is tested.",
            howToFix = "Add a string or plurals resource and read it with stringResource or pluralStringResource. Compute the values the text shows in the mapper or view model and pass them to the resource.",
            badExample = "is ProfileDetailRowUIModel.Age -> \"${'$'}ageInYears years\"",
            goodExample = "is ProfileDetailRowUIModel.Age -> pluralStringResource(R.plurals.profile_age, ageInYears, ageInYears)",
        )
    }
}
