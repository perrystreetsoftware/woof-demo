package com.perrystreet.woof.konsist.designsystem

import com.lemonappdev.konsist.api.ext.list.modifierprovider.withPublicOrDefaultModifier
import com.lemonappdev.konsist.api.ext.list.withAnnotationNamed
import com.perrystreet.woof.konsist.Assertions.assertFalse
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class TemplatesDoNotExposeModifiers : BehaviorSpec() {
    init {
        Given("A public Template composable") {
            val templates = KonsistUtils.atomicDesignModule.functions()
                .withAnnotationNamed("Composable")
                .withPublicOrDefaultModifier()
                .filter { it.path.contains("/atomic/templates/") }

            Then("It does not take a Modifier parameter") {
                templates.assertFalse(message = Message) { template -> template.parameters.any { it.type.name == "Modifier" } }
            }

            Then("It exposes at least one slot") {
                templates.assertFalse(message = Message) { template -> template.parameters.none { it.type.text.contains("@Composable") || it.type.text.contains("Scope.()") } }
            }
        }
    }

    private companion object {
        private val Message = LintRuleMessage(
            rule = "Templates expose slots, never a Modifier.",
            why = "A template owns the page layout. If a screen could pass a Modifier, paddings and sizes would again be decided outside the design system.",
            howToFix = "Build layout parameters into the template and expose @Composable or *Scope.() -> Unit slots for content.",
            badExample = "@Composable fun TemplateGrid(modifier: Modifier, content: LazyGridScope.() -> Unit)",
            goodExample = "@Composable fun TemplateGrid(topBar: @Composable () -> Unit, content: LazyGridScope.() -> Unit)",
        )
    }
}
