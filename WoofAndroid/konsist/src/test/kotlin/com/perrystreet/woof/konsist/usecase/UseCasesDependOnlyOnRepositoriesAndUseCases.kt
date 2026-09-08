package com.perrystreet.woof.konsist.usecase

import com.perrystreet.woof.konsist.Assertions.assertTrue
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.KonsistUtils.constructorParameterTypeNames
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class UseCasesDependOnlyOnRepositoriesAndUseCases : BehaviorSpec() {
    init {
        Given("A UseCase") {
            val useCases = KonsistUtils.useCases

            Then("It only injects repositories and other use cases") {
                useCases.assertTrue(message = Message) { useCase ->
                    useCase.constructorParameterTypeNames.all { it.endsWith("Repository") || it.endsWith("UseCase") }
                }
            }
        }
    }

    private companion object {
        private val Message = LintRuleMessage(
            rule = "UseCases may only depend on Repositories and other UseCases.",
            why = "Use cases compose data access into business rules. Data sources, mappers, or view types would leak other layers into the domain.",
            howToFix = "Inject a Repository that wraps the data source, or another UseCase.",
            badExample = "class SendWoofUseCase(private val dataSource: IWoofsDataSource)",
            goodExample = "class SendWoofUseCase(private val woofsRepository: WoofsRepository)",
        )
    }
}
