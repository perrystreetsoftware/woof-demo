package com.perrystreet.woof.presentation.profile.ui.extensions

import androidx.annotation.StringRes
import com.perrystreet.woof.presentation.profile.uimodel.ProfileModerationDialogUIModel
import com.perrystreet.woof.resources.R

object ProfileModerationDialogUIModelExtensions {
    @StringRes
    fun ProfileModerationDialogUIModel.titleRes(): Int = when (this) {
        is ProfileModerationDialogUIModel.Report -> R.string.profile_report_title
        is ProfileModerationDialogUIModel.Block -> R.string.profile_block_title
    }

    @StringRes
    fun ProfileModerationDialogUIModel.messageRes(): Int = when (this) {
        is ProfileModerationDialogUIModel.Report -> R.string.profile_report_message
        is ProfileModerationDialogUIModel.Block -> R.string.profile_block_message
    }

    @StringRes
    fun ProfileModerationDialogUIModel.confirmRes(): Int = when (this) {
        is ProfileModerationDialogUIModel.Report -> R.string.profile_report_confirm
        is ProfileModerationDialogUIModel.Block -> R.string.profile_block_confirm
    }
}
