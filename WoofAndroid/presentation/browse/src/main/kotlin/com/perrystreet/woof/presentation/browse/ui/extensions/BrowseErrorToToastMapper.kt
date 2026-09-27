package com.perrystreet.woof.presentation.browse.ui.extensions

import com.perrystreet.woof.presentation.common.error.ErrorToast
import com.perrystreet.woof.presentation.common.error.IErrorToToastMapper
import com.perrystreet.woof.resources.R

class BrowseErrorToToastMapper : IErrorToToastMapper {
    override fun invoke(error: Throwable): ErrorToast? = ErrorToast(messageRes = R.string.browse_error_message)
}
