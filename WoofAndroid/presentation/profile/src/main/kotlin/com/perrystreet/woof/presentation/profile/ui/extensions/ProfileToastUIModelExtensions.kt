package com.perrystreet.woof.presentation.profile.ui.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.perrystreet.woof.presentation.profile.uimodel.ProfileToastUIModel
import com.perrystreet.woof.resources.R

object ProfileToastUIModelExtensions {
    @Composable
    fun ProfileToastUIModel.text(): String = when (this) {
        is ProfileToastUIModel.WoofSent -> stringResource(R.string.profile_toast_woof_sent, name)
        is ProfileToastUIModel.MessageSent -> stringResource(R.string.profile_toast_message_sent, name)
        ProfileToastUIModel.ReportSent -> stringResource(R.string.profile_toast_report_sent)
    }
}
