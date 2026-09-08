package com.perrystreet.woof.konsist.di

import com.perrystreet.woof.konsist.Assertions.assertFalse
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.KonsistUtils.hasKoinDefinition
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class NoNullableInjection : BehaviorSpec() {
    init {
        Given("A constructor parameter of a Koin-managed class") {
            val parameters = KonsistUtils.productionCode.classes()
                .filter { it.hasKoinDefinition() }
                .flatMap { it.primaryConstructor?.parameters.orEmpty() }

            Then("It is not nullable") {
                parameters.assertFalse(message = Message) { it.type.isNullable }
            }
        }
    }

    private companion object {
        private val Message = LintRuleMessage(
            rule = "Koin-managed classes never inject nullable dependencies.",
            why = "A nullable dependency is a missing definition in disguise. Either the dependency exists in the graph or the design is wrong.",
            howToFix = "Make the dependency non-null and provide it in the DI graph, or split the class.",
            badExample = "class DogsRepository(private val dataSource: IDogsDataSource?)",
            goodExample = "class DogsRepository(private val dataSource: IDogsDataSource)",
        )
    }
}
