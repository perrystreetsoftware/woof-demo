package com.perrystreet.woof.konsist.repositories

import com.lemonappdev.konsist.api.ext.list.functions
import com.lemonappdev.konsist.api.ext.list.modifierprovider.withPublicOrDefaultModifier
import com.lemonappdev.konsist.api.ext.list.properties
import com.perrystreet.woof.konsist.Assertions.assertFalse
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class RepositoryFunctionsDoNotReturnDTOs : BehaviorSpec() {
    init {
        Given("A public member of a Repository") {
            val functions = KonsistUtils.repositories.functions().withPublicOrDefaultModifier()
            val properties = KonsistUtils.repositories.properties().withPublicOrDefaultModifier()

            Then("Its function return types never mention a DTO") {
                functions.assertFalse(message = Message) { it.returnType?.text?.contains("DTO") }
            }

            Then("Its property types never mention a DTO") {
                properties.assertFalse(message = Message) { it.type?.text?.contains("DTO") }
            }
        }
    }

    private companion object {
        private val Message = LintRuleMessage(
            rule = "Repositories never expose DTOs.",
            why = "The repository is the boundary where transport data becomes domain data. Leaking a DTO pushes mapping into every caller.",
            howToFix = "Map with a DTOToDomainMapper inside the repository and return the domain model.",
            badExample = "fun getDogs(): Single<DogsPageDTO>",
            goodExample = "fun getDogs(): Single<DogsPage> = dataSource.getDogs().map { pageMapper(it) }",
        )
    }
}
