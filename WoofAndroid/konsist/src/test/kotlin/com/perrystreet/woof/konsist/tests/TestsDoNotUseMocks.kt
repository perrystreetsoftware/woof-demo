package com.perrystreet.woof.konsist.tests

import com.perrystreet.woof.konsist.Assertions.assertFalse
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class TestsDoNotUseMocks : BehaviorSpec() {
    init {
        Given("A test file") {
            val files = KonsistUtils.testCode.files

            Then("It does not import a mocking library") {
                files.assertFalse(message = Message) { file ->
                    file.hasImport { it.name.startsWith("io.mockk") || it.name.startsWith("org.mockito") }
                }
            }
        }
    }

    private companion object {
        private val Message = LintRuleMessage(
            rule = "Tests never use mocking libraries.",
            why = """
                Mocks couple tests to implementation details and let broken collaborations pass. The data source layer
                has hand-written fakes configured through *DataSourceFactory builders, and everything above it runs for real.
            """.trimIndent(),
            howToFix = "Configure the fake data source with a factory, e.g. DogsDataSourceFactory().withDogs(450).",
            badExample = "val useCase = mockk<GetDogsFeedUseCase>()",
            goodExample = "DogsDataSourceFactory().withDogs(450)",
        )
    }
}
