package com.perrystreet.woof.presentation.profile.ui.extensions

import com.perrystreet.woof.models.errors.MessageException
import com.perrystreet.woof.models.errors.WoofException
import com.perrystreet.woof.presentation.common.error.ErrorToast
import com.perrystreet.woof.presentation.common.error.IErrorToToastMapper
import com.perrystreet.woof.resources.R

class ProfileErrorToToastMapper(
    private val name: String,
) : IErrorToToastMapper {
    override fun invoke(error: Throwable): ErrorToast? = when (error) {
        WoofException.AlreadyWoofed -> ErrorToast(R.string.profile_toast_already_woofed, listOf(name))
        MessageException.EmptyMessage -> ErrorToast(R.string.profile_toast_message_empty)
        else -> null
    }
}
