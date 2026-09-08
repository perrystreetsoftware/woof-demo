package com.perrystreet.woof.konsist.viewmodel

import com.perrystreet.woof.konsist.Assertions.assertTrue
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class ViewModelsInheritFromBaseViewModels : BehaviorSpec() {
    init {
        Given("A ViewModel") {
            val viewModels = KonsistUtils.viewModels

            Then("It extends one of the shared base ViewModels") {
                viewModels.assertTrue(message = Message) { viewModel ->
                    viewModel.hasParent { parent -> parent.name.substringBefore("<") in AllowedParents }
                }
            }
        }
    }

    private companion object {
        private val AllowedParents = arrayOf("StateProducingViewModel", "StateDerivingViewModel", "StatelessViewModel")

        private val Message = LintRuleMessage(
            rule = "ViewModels must extend StateProducingViewModel, StateDerivingViewModel, or StatelessViewModel.",
            why = """
                The base classes own the disposables, the error channel, the lifecycle hooks, and the UiObservable
                state contract. Extending androidx ViewModel directly loses all of that.
            """.trimIndent(),
            howToFix = "Pick StateProducingViewModel when the ViewModel mutates state, StateDerivingViewModel when state is combined from streams, StatelessViewModel otherwise.",
            badExample = "class GridViewModel : ViewModel()",
            goodExample = "class GridViewModel : StateProducingViewModel<GridViewModel.State>(State.Loading)",
        )
    }
}
