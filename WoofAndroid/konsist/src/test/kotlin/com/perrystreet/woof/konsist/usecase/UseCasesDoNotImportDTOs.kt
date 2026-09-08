package com.perrystreet.woof.konsist.usecase

import com.perrystreet.woof.konsist.Assertions.assertFalse
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class UseCasesDoNotImportDTOs : BehaviorSpec() {
    init {
        Given("A file in the use case or domain model module") {
            val files = (KonsistUtils.useCaseModule + KonsistUtils.domainModelModule).files

            Then("It does not import DTOs, data sources, or Moshi") {
                files.assertFalse(message = Message) { file ->
                    file.hasImport { import ->
                        import.name.startsWith("com.perrystreet.woof.dto") ||
                            import.name.startsWith("com.perrystreet.woof.datasource") ||
                            import.name.startsWith("com.squareup.moshi")
                    }
                }
            }
        }
    }

    private companion object {
        private val Message = LintRuleMessage(
            rule = "The domain layer must not know about DTOs, data sources, or Moshi.",
            why = "DTOs mirror a transport format. If the domain depended on them, every server change would ripple through business rules.",
            howToFix = "Map the DTO to a domain model in the repository and consume the domain model.",
            badExample = "operator fun invoke(): Single<DogsPageDTO>",
            goodExample = "operator fun invoke(): Single<DogsPage>",
        )
    }
}
