package com.perrystreet.woof.konsist.tests

import com.perrystreet.woof.konsist.Assertions.assertTrue
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class ViewModelsHaveTests : BehaviorSpec() {
    init {
        Given("A ViewModel") {
            val viewModels = KonsistUtils.viewModels
            val testClassNames = KonsistUtils.testCode.classes().map { it.name }

            Then("A matching *ViewModelTest exists") {
                viewModels.assertTrue(message = Message) { viewModel -> "${viewModel.name}Test" in testClassNames }
            }
        }
    }

    private companion object {
        private val Message = LintRuleMessage(
            rule = "Every ViewModel has a <Name>Test.",
            why = "The ViewModel test is the single place where a feature's behaviour is specified end to end, from user action down to the fake data source.",
            howToFix = "Add <Name>Test extending ViewModelBehaviorSpec in the feature's test source set.",
            badExample = "class ProfileWoofViewModel  // no ProfileWoofViewModelTest",
            goodExample = "class ProfileWoofViewModelTest : ViewModelBehaviorSpec()",
        )
    }
}
