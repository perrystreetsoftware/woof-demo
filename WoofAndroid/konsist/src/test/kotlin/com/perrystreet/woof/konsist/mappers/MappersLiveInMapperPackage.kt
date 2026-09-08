package com.perrystreet.woof.konsist.mappers

import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.perrystreet.woof.konsist.Assertions.assertTrue
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class MappersLiveInMapperPackage : BehaviorSpec() {
    init {
        Given("A DTOToDomain mapper") {
            val mappers = KonsistUtils.productionCode.classes().withNameEndingWith("DTOToDomainMapper")

            Then("It lives in a repositories mapper package") {
                mappers.assertTrue(message = Message) { it.resideInPackage("com.perrystreet.woof.repositories..mapper") }
            }
        }

        Given("A DomainToUIModel mapper") {
            val mappers = KonsistUtils.productionCode.classes().withNameEndingWith("UIModelMapper")

            Then("It lives in a feature mapper package") {
                mappers.assertTrue(message = Message) { it.resideInPackage("com.perrystreet.woof.presentation..mapper") }
            }
        }
    }

    private companion object {
        private val Message = LintRuleMessage(
            rule = "DTOToDomain mappers live next to their repository; DomainToUIModel mappers live in presentation/<feature>/mapper.",
            why = "The mapper's location tells you which boundary it crosses. Keeping them with the layer that owns the conversion avoids circular dependencies.",
            howToFix = "Move the mapper to the mapper package of the repository or feature that uses it.",
            badExample = "package com.perrystreet.woof.usecase.dogs\nclass DogDTOToDomainMapper",
            goodExample = "package com.perrystreet.woof.repositories.dogs.mapper\nclass DogDTOToDomainMapper",
        )
    }
}
