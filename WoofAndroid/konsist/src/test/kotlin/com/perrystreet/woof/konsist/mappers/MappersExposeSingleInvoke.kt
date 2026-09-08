package com.perrystreet.woof.konsist.mappers

import com.lemonappdev.konsist.api.ext.list.modifierprovider.withPublicOrDefaultModifier
import com.perrystreet.woof.konsist.Assertions.assertTrue
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class MappersExposeSingleInvoke : BehaviorSpec() {
    init {
        Given("A DTOToDomain or DomainToUIModel mapper") {
            val mappers = KonsistUtils.mappers

            Then("It is a @Factory class with a single operator fun invoke") {
                mappers.assertTrue(message = Message) { mapper ->
                    val publicFunctions = mapper.functions().withPublicOrDefaultModifier()
                    mapper.hasAnnotationWithName("Factory") &&
                        publicFunctions.size == 1 &&
                        publicFunctions.single().let { it.name == "invoke" && it.hasOperatorModifier }
                }
            }
        }
    }

    private companion object {
        private val Message = LintRuleMessage(
            rule = "Mappers are @Factory classes exposing exactly one operator fun invoke().",
            why = "A mapper converts one type into another and nothing else. Injecting it as a class (not an object) keeps nested mappers composable.",
            howToFix = "Annotate with @Factory, name the conversion function invoke, and split extra conversions into their own mappers.",
            badExample = "object DogMapper { fun toDomain(dto: DogDTO): Dog; fun toUIModel(dog: Dog): DogCellUIModel }",
            goodExample = "@Factory\nclass DogDTOToDomainMapper { operator fun invoke(dto: DogDTO): Dog }",
        )
    }
}
