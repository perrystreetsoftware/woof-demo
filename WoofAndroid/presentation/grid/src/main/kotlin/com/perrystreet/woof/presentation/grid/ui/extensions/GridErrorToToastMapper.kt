package com.perrystreet.woof.presentation.grid.ui.extensions

import com.perrystreet.woof.presentation.common.error.ErrorToast
import com.perrystreet.woof.presentation.common.error.IErrorToToastMapper
import com.perrystreet.woof.resources.R

class GridErrorToToastMapper : IErrorToToastMapper {
    override fun invoke(error: Throwable): ErrorToast? = ErrorToast(messageRes = R.string.grid_error_message)
}
