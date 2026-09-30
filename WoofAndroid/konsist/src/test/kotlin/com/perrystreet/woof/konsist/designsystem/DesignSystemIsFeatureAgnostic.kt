package com.perrystreet.woof.konsist.designsystem

import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.perrystreet.woof.konsist.Assertions.assertFalse
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class DesignSystemIsFeatureAgnostic : BehaviorSpec() {
    init {
        Given("A name declared in the atomic design system") {
            val vocabulary = featureVocabulary()
            val scope = KonsistUtils.atomicDesignModule
            val declarations: List<KoNameProvider> =
                scope.classes() + scope.interfaces() + scope.objects() + scope.functions() + scope.properties() +
                    scope.classes().flatMap { it.enumConstants }
            val parameters: List<KoNameProvider> =
                scope.functions().flatMap { it.parameters } +
                    scope.classes().flatMap { it.primaryConstructor?.parameters.orEmpty() }

            Then("Components, types, role entries and properties are named by their shape") {
                declarations.assertFalse(message = Message) { it.name.usesAnyOf(vocabulary) }
            }

            Then("Parameters are named by the data they hold") {
                parameters.assertFalse(message = Message) { it.name.usesAnyOf(vocabulary) }
            }

            Then("Its packages are named by shape") {
                scope.files.assertFalse(message = Message) { file ->
                    file.packagee?.name.orEmpty().substringAfter(".atomic.", "").split(".").any { it.usesAnyOf(vocabulary) }
                }
            }

            Then("The resources it references belong to no feature") {
                scope.files.assertFalse(message = Message) { file ->
                    ResourceReference.findAll(file.text).any { it.groupValues[1].usesAnyOf(vocabulary) }
                }
            }
        }
    }

    private companion object {
        private val FeatureModule = Regex("/presentation/([a-z]+)/")
        private val WordBoundary = Regex("(?<=[a-z0-9])(?=[A-Z])|_")
        private val ResourceReference = Regex("""\bR\.\w+\.(\w+)""")

        fun featureVocabulary(): Set<String> {
            val features = KonsistUtils.featureModules.files.mapNotNull { FeatureModule.find(it.path)?.groupValues?.get(1) }
            val entities = KonsistUtils.domainModelModule.classes()
                .filterNot { it.name.endsWith("Exception") }
                .map { it.name.words().first() }
            return (features + entities).flatMap { word -> listOf(word.lowercase(), word.lowercase().removeSuffix("s")) }.toSet()
        }

        fun String.words(): List<String> = split(WordBoundary).filter { it.isNotEmpty() }

        fun String.usesAnyOf(vocabulary: Set<String>): Boolean = words().any { it.lowercase() in vocabulary }

        private val Message = LintRuleMessage(
            rule = "The design system is feature-agnostic: names and the resources it references describe shape and data, never a feature.",
            why = "Every feature can use a design-system component, so it is named by what it looks like, its parameters by the data they hold, and its roles by the style they pick. A feature word in the design system, or a feature's string or icon inside a component, ties the component to one screen, and the next feature builds a twin instead of reusing it. Feature words are the feature module names and the domain model names.",
            howToFix = "Rename the component, its package, role entry or parameter after its structure or what it carries. Pass a feature's text and icons in as parameters; the feature's own words and resources stay in its presentation module.",
            badExample = "@Composable fun OrgFavoriteCard(dogName: String, photo: AsyncImageState, onFavoriteTap: () -> Unit)",
            goodExample = "@Composable fun OrgPhotoCard(title: String, imageState: AsyncImageState, contentDescription: String, onTap: () -> Unit)",
        )
    }
}
