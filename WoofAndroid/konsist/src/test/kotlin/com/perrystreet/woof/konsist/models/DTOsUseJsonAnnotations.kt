package com.perrystreet.woof.konsist.models

import com.lemonappdev.konsist.api.ext.list.modifierprovider.withDataModifier
import com.perrystreet.woof.konsist.Assertions.assertTrue
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class DTOsUseJsonAnnotations : BehaviorSpec() {
    init {
        Given("A data class in the dto module") {
            val dtos = KonsistUtils.dtoModule.classes().withDataModifier()

            Then("Its name ends with DTO") {
                dtos.assertTrue(message = Message) { it.name.endsWith("DTO") }
            }

            Then("It is annotated with @JsonClass and every property with @Json") {
                dtos.assertTrue(message = Message) { dto ->
                    dto.hasAnnotationWithName("JsonClass") &&
                        dto.primaryConstructor?.parameters.orEmpty().all { it.hasAnnotationWithName("Json") }
                }
            }
        }
    }

    private companion object {
        private val Message = LintRuleMessage(
            rule = "DTOs end with DTO, use @JsonClass(generateAdapter = true), and annotate every property with @Json(name = ...).",
            why = "DTOs are the contract with the transport layer. Explicit JSON names survive obfuscation and renames, and the suffix makes it obvious the type must be mapped before use.",
            howToFix = "Add the DTO suffix and the Moshi annotations.",
            badExample = "data class Dog(val id: Long, val photoUrl: String)",
            goodExample = "@JsonClass(generateAdapter = true)\ndata class DogDTO(@Json(name = \"id\") val id: Long, @Json(name = \"photo_url\") val photoUrl: String)",
        )
    }
}
