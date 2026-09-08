package com.perrystreet.woof.presentation.account.ui.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import com.perrystreet.woof.models.dog.DogSize
import com.perrystreet.woof.presentation.account.uimodel.AccountDetailRowUIModel
import com.perrystreet.woof.resources.R

object AccountDetailRowUIModelExtensions {
    @Composable
    fun AccountDetailRowUIModel.label(): String = when (this) {
        is AccountDetailRowUIModel.Breed -> stringResource(R.string.profile_label_breed)
        is AccountDetailRowUIModel.Age -> stringResource(R.string.profile_label_age)
        is AccountDetailRowUIModel.Size -> stringResource(R.string.profile_label_size)
        is AccountDetailRowUIModel.Neighborhood -> stringResource(R.string.profile_label_neighborhood)
        is AccountDetailRowUIModel.FavoriteActivity -> stringResource(R.string.profile_label_favorite_activity)
    }

    @Composable
    fun AccountDetailRowUIModel.value(): String = when (this) {
        is AccountDetailRowUIModel.Breed -> breed
        is AccountDetailRowUIModel.Age -> pluralStringResource(R.plurals.profile_age, ageInYears, ageInYears)
        is AccountDetailRowUIModel.Size -> size.text()
        is AccountDetailRowUIModel.Neighborhood -> neighborhood
        is AccountDetailRowUIModel.FavoriteActivity -> activity
    }

    @Composable
    private fun DogSize.text(): String = when (this) {
        DogSize.Small -> stringResource(R.string.profile_size_small)
        DogSize.Medium -> stringResource(R.string.profile_size_medium)
        DogSize.Large -> stringResource(R.string.profile_size_large)
    }
}
