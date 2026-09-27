package com.perrystreet.woof.konsist.viewmodel

import com.perrystreet.woof.konsist.Assertions.assertFalse
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class PresentationDoesNotBuildDomainModels : BehaviorSpec() {
    init {
        Given("A presentation file outside previews") {
            val files = KonsistUtils.presentationModules.files.filter { !it.path.contains("/preview/") }
            val models = KonsistUtils.domainModelModule.classes(includeNested = false).map { it.name }
            val construction = Regex("""\b(${models.joinToString("|")})\(""")

            Then("It receives domain models from use cases instead of constructing them") {
                files.assertFalse(message = Message) { construction.containsMatchIn(it.text) }
            }
        }
    }

    private companion object {
        private val Message = LintRuleMessage(
            rule = "Presentation code receives domain models from use cases; only previews construct them.",
            why = "Domain models come from a data source through a repository and a use case. A view model that builds its own list skips those layers, so the screen shows data the app never loads, and its tests check the literals instead of the behaviour.",
            howToFix = "Add the data source, repository and use case the way the reference feature does, inject the use case into the view model and derive the state from it. Keep hand-made models in ui/preview for previews and in testFixtures for tests.",
            badExample = "// FavoritesViewModel\noverride val state = Observable.just(listOf(Dog(id = 1L, name = \"Rufus\", photoUrl = \"\"))).map { ... }",
            goodExample = "// FavoritesViewModel\noverride val state = getFavoriteDogsUseCase().map { dogs -> ... }",
        )
    }
}
