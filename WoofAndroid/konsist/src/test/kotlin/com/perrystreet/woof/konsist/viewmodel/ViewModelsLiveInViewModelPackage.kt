package com.perrystreet.woof.konsist.viewmodel

import com.perrystreet.woof.konsist.Assertions.assertTrue
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class ViewModelsLiveInViewModelPackage : BehaviorSpec() {
    init {
        Given("A ViewModel") {
            val viewModels = KonsistUtils.viewModels

            Then("It is declared in the feature's viewmodel package") {
                viewModels.assertTrue(message = Message) { it.resideInPackage("com.perrystreet.woof.presentation..viewmodel") }
            }
        }
    }

    private companion object {
        private val Message = LintRuleMessage(
            rule = "ViewModels live in presentation/<feature>/viewmodel.",
            why = "Each feature module has the same four folders (viewmodel, uimodel, mapper, ui), so anyone can find a class by convention.",
            howToFix = "Move the class to the viewmodel package of its feature.",
            badExample = "package com.perrystreet.woof.presentation.grid.ui\nclass GridViewModel",
            goodExample = "package com.perrystreet.woof.presentation.grid.viewmodel\nclass GridViewModel",
        )
    }
}
