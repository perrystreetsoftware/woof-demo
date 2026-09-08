package com.perrystreet.woof.konsist.repositories

import com.perrystreet.woof.konsist.Assertions.assertTrue
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.KonsistUtils.constructorParameterTypeNames
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class RepositoriesDependOnDataSourceInterfaces : BehaviorSpec() {
    init {
        Given("A Repository") {
            val repositories = KonsistUtils.repositories

            Then("It depends only on data source interfaces and mappers") {
                repositories.assertTrue(message = Message) { repository ->
                    repository.constructorParameterTypeNames.all { type ->
                        (type.startsWith("I") && type.endsWith("DataSource")) || type.endsWith("Mapper")
                    }
                }
            }
        }
    }

    private companion object {
        private val Message = LintRuleMessage(
            rule = "Repositories depend on I*DataSource interfaces and mappers, never on concrete data sources or other repositories.",
            why = """
                Programming against the interface is what lets tests swap the data source for a fake without
                mocks, and keeps repositories from reaching into each other's state.
            """.trimIndent(),
            howToFix = "Inject the I*DataSource interface. Compose repositories in a UseCase instead of injecting one into another.",
            badExample = "class DogsRepository(private val dataSource: DogsLocalDataSource, private val moderation: ModerationRepository)",
            goodExample = "class DogsRepository(private val dataSource: IDogsDataSource, private val pageMapper: DogsPageDTOToDomainMapper)",
        )
    }
}
