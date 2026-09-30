package com.perrystreet.woof.konsist.designsystem

import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.ext.list.withAnnotationNamed
import com.perrystreet.woof.konsist.Assertions.assertFalse
import com.perrystreet.woof.konsist.KonsistUtils
import com.perrystreet.woof.konsist.KonsistUtils.body
import com.perrystreet.woof.konsist.LintRuleMessage
import io.kotest.core.spec.style.BehaviorSpec

class OrganismsReuseExistingOrganisms : BehaviorSpec() {
    init {
        Given("An organism in the atomic design system") {
            val components = KonsistUtils.atomicDesignModule.functions().withAnnotationNamed("Composable").map { it.name }.toSet()
            val organisms = KonsistUtils.atomicDesignModule.files
                .filter { it.path.contains("/organisms/") }
                .flatMap { file ->
                    val composables = file.functions().withAnnotationNamed("Composable").filter { it.isTopLevel }
                    val helpers = composables.filter { it.hasPrivateModifier || it.hasInternalModifier }.associateBy { it.name }
                    composables
                        .filter { it.name.startsWith("Org") && it !in helpers.values }
                        .map { it to it.componentCalls(components, helpers) }
                }
                .toMap()
            val rebuilt = organisms.flatMap { (organism, calls) ->
                organisms
                    .filter { (existing, parts) -> existing != organism && parts.values.sum() >= 2 && existing.name !in calls && calls.containsAll(parts) }
                    .map { (existing, _) -> organism.name to existing.name }
            }

            Then("It calls every existing organism whose parts it contains") {
                organisms.keys.toList().assertFalse(message = message(rebuilt)) { organism -> rebuilt.any { it.first == organism.name } }
            }
        }
    }

    private companion object {
        private val ComponentCall = Regex("""\b([A-Z]\w*)\(""")

        fun KoFunctionDeclaration.componentCalls(
            components: Set<String>,
            helpers: Map<String, KoFunctionDeclaration>,
        ): Map<String, Int> {
            val calls = ComponentCall.findAll(body).map { it.groupValues[1] }.filter { it != name }.toList()
            val expanded = calls.flatMap { call ->
                helpers[call]?.let { helper -> ComponentCall.findAll(helper.body).map { it.groupValues[1] }.toList() } ?: listOf(call)
            }
            return expanded.filter { it in components && it !in helpers }.groupingBy { it }.eachCount()
        }

        fun Map<String, Int>.containsAll(parts: Map<String, Int>): Boolean =
            parts.all { (component, count) -> (this[component] ?: 0) >= count }

        fun message(rebuilt: List<Pair<String, String>>) = LintRuleMessage(
            rule = "An organism that contains every part of an existing organism calls that organism instead of rebuilding it.",
            why = "Two organisms made of the same parts drift apart: a fix or a new size lands in one and not the other, and the design system grows a near-twin for every screen. Calling the existing organism keeps one source for that shape.",
            howToFix = "Call the existing organism inside the new one and add only what it lacks. Where the new one looks different, such as another size or typography, add an entry to the existing organism's roles/ enum and pass it. " +
                rebuilt.joinToString(" ") { (organism, existing) -> "$organism rebuilds $existing." },
            badExample = """
                @Composable fun OrgErrorState(title: String, message: String, actionText: String, onActionTap: () -> Unit) {
                    Column {
                        AtomAppLogo(...); AtomText(text = title, ...); AtomText(text = message, ...)
                        MolButton(text = actionText, onTap = onActionTap)
                    }
                }
                // OrgEmptyState already draws AtomAppLogo + AtomText + AtomText
            """.trimIndent(),
            goodExample = """
                @Composable fun OrgErrorState(title: String, message: String, actionText: String, onActionTap: () -> Unit) {
                    Column {
                        OrgEmptyState(title = title, message = message)
                        MolButton(text = actionText, onTap = onActionTap)
                    }
                }
            """.trimIndent(),
        )
    }
}
