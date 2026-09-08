package com.perrystreet.woof.presentation.profile.uimodel

import com.perrystreet.woof.models.dog.DogSize

sealed class ProfileDetailRowUIModel {
    data class Breed(val breed: String) : ProfileDetailRowUIModel()

    data class Age(val ageInYears: Int) : ProfileDetailRowUIModel()

    data class Size(val size: DogSize) : ProfileDetailRowUIModel()

    data class Neighborhood(val neighborhood: String) : ProfileDetailRowUIModel()

    data class FavoriteActivity(val activity: String) : ProfileDetailRowUIModel()
}
