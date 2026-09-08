package com.perrystreet.woof.presentation.profile.ui.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import com.perrystreet.woof.models.dog.DogSize
import com.perrystreet.woof.presentation.profile.uimodel.ProfileDetailRowUIModel
import com.perrystreet.woof.resources.R

object ProfileDetailRowUIModelExtensions {
    @Composable
    fun ProfileDetailRowUIModel.label(): String = when (this) {
        is ProfileDetailRowUIModel.Breed -> stringResource(R.string.profile_label_breed)
        is ProfileDetailRowUIModel.Age -> stringResource(R.string.profile_label_age)
        is ProfileDetailRowUIModel.Size -> stringResource(R.string.profile_label_size)
        is ProfileDetailRowUIModel.Neighborhood -> stringResource(R.string.profile_label_neighborhood)
        is ProfileDetailRowUIModel.FavoriteActivity -> stringResource(R.string.profile_label_favorite_activity)
    }

    @Composable
    fun ProfileDetailRowUIModel.value(): String = when (this) {
        is ProfileDetailRowUIModel.Breed -> breed
        is ProfileDetailRowUIModel.Age -> pluralStringResource(R.plurals.profile_age, ageInYears, ageInYears)
        is ProfileDetailRowUIModel.Size -> size.text()
        is ProfileDetailRowUIModel.Neighborhood -> neighborhood
        is ProfileDetailRowUIModel.FavoriteActivity -> activity
    }

    @Composable
    private fun DogSize.text(): String = when (this) {
        DogSize.Small -> stringResource(R.string.profile_size_small)
        DogSize.Medium -> stringResource(R.string.profile_size_medium)
        DogSize.Large -> stringResource(R.string.profile_size_large)
    }
}
