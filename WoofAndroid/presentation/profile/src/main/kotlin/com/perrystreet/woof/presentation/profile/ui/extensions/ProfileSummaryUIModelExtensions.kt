package com.perrystreet.woof.presentation.profile.ui.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import com.perrystreet.woof.presentation.profile.uimodel.ProfileSummaryUIModel
import com.perrystreet.woof.resources.R

object ProfileSummaryUIModelExtensions {
    @Composable
    fun ProfileSummaryUIModel.text(): String = stringResource(
        R.string.profile_summary,
        pluralStringResource(R.plurals.profile_age, ageInYears, ageInYears),
        breed,
        neighborhood,
    )
}
