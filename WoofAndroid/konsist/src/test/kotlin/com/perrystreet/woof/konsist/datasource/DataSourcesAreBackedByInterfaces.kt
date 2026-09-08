package com.perrystreet.woof.konsist.datasource

import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.perrystreet.woof.konsist.Assertions.assertTrue
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class DataSourcesAreBackedByInterfaces : BehaviorSpec() {
    init {
        Given("A data source interface") {
            val interfaces = KonsistUtils.dataSourceModule.interfaces().withNameEndingWith("DataSource")

            Then("Its name starts with I") {
                interfaces.assertTrue(message = Message) { it.name.startsWith("I") }
            }
        }

        Given("A data source implementation") {
            val implementations = KonsistUtils.dataSourceModule.classes().withNameEndingWith("DataSource")

            Then("It implements an I*DataSource interface") {
                implementations.assertTrue(message = Message) { implementation ->
                    implementation.hasParent { parent -> parent.name.startsWith("I") && parent.name.endsWith("DataSource") }
                }
            }
        }
    }

    private companion object {
        private val Message = LintRuleMessage(
            rule = "Every data source is an I*DataSource interface with local and fake implementations.",
            why = "The interface is the seam between the app and the outside world. Repositories, tests, and a future remote implementation all program against it.",
            howToFix = "Declare interface IFooDataSource, then make FooLocalDataSource and FakeFooDataSource implement it.",
            badExample = "class DogsLocalDataSource { fun getDogs(): Single<DogsPageDTO> }",
            goodExample = "interface IDogsDataSource { fun getDogs(): Single<DogsPageDTO> }\nclass DogsLocalDataSource : IDogsDataSource",
        )
    }
}
