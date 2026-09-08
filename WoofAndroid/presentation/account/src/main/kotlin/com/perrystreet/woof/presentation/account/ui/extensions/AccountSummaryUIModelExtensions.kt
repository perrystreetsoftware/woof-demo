package com.perrystreet.woof.presentation.account.ui.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import com.perrystreet.woof.presentation.account.uimodel.AccountSummaryUIModel
import com.perrystreet.woof.resources.R

object AccountSummaryUIModelExtensions {
    @Composable
    fun AccountSummaryUIModel.text(): String = stringResource(
        R.string.profile_summary,
        pluralStringResource(R.plurals.profile_age, ageInYears, ageInYears),
        breed,
        neighborhood,
    )
}
