package com.perrystreet.woof.presentation.common.error

import androidx.annotation.StringRes

data class ErrorToast(
    @StringRes val messageRes: Int,
    val formatArgs: List<Any> = emptyList(),
)
