package com.perrystreet.woof.konsist

import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.verify.assertEmpty
import com.lemonappdev.konsist.api.verify.assertFalse
import com.lemonappdev.konsist.api.verify.assertTrue

object Assertions {
    fun <E : KoBaseProvider> List<E?>.assertTrue(
        message: LintRuleMessage,
        predicate: (E) -> Boolean?,
    ) {
        assertTrue(additionalMessage = message.formatted, function = predicate)
    }

    fun <E : KoBaseProvider> List<E?>.assertFalse(
        message: LintRuleMessage,
        predicate: (E) -> Boolean?,
    ) {
        assertFalse(additionalMessage = message.formatted, function = predicate)
    }

    fun <E : KoBaseProvider> List<E?>.assertEmpty(message: LintRuleMessage) {
        assertEmpty(additionalMessage = message.formatted)
    }
}
