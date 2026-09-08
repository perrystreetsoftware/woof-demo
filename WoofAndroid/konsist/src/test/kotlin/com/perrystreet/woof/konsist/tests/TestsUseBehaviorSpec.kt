package com.perrystreet.woof.konsist.tests

import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.perrystreet.woof.konsist.Assertions.assertTrue
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class TestsUseBehaviorSpec : BehaviorSpec() {
    init {
        Given("A test class") {
            val tests = KonsistUtils.testCode.classes().withNameEndingWith("Test")

            Then("It extends ViewModelBehaviorSpec and reads as Given / When / Then") {
                tests.assertTrue(message = Message) { test ->
                    test.hasParentWithName("ViewModelBehaviorSpec") &&
                        test.text.contains("Given(") &&
                        test.text.contains("Then(")
                }
            }
        }

        Given("A test file") {
            val files = KonsistUtils.testCode.files

            Then("It does not use beforeTest, Thread.sleep, or other spec styles") {
                files.assertTrue(message = Message) { file ->
                    !file.text.contains("beforeTest") &&
                        !file.text.contains("Thread.sleep") &&
                        !file.hasImport { it.name.contains("DescribeSpec") || it.name.contains("FunSpec") || it.name.contains("StringSpec") }
                }
            }
        }
    }

    private companion object {
        private val Message = LintRuleMessage(
            rule = "Tests are ViewModelBehaviorSpecs written as Given / When / Then with beforeEach setup.",
            why = """
                Tests target the ViewModel layer only, which exercises use cases and repositories underneath.
                The BDD structure reads as a spec, and InstancePerLeaf plus beforeEach gives every Then a clean slate.
            """.trimIndent(),
            howToFix = "Extend ViewModelBehaviorSpec, use Given/When/Then/And blocks, set up in beforeEach, and advance time with TimeAdvancingFactory().tick().",
            badExample = "class GridViewModelTest : FunSpec({ test(\"loads\") { Thread.sleep(100) } })",
            goodExample = "class GridViewModelTest : ViewModelBehaviorSpec() { init { Given(\"I open the grid\") { Then(\"...\") { ... } } } }",
        )
    }
}
