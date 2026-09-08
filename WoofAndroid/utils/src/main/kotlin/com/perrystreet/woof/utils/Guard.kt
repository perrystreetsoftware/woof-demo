package com.perrystreet.woof.utils

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
inline fun guard(condition: Boolean, returnStatement: () -> Unit) {
    contract {
        returns() implies condition
    }

    if (!condition) {
        returnStatement()
    }
}
