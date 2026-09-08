package com.perrystreet.woof.presentation.account.uimodel

import com.perrystreet.woof.models.dog.DogSize

sealed class AccountDetailRowUIModel {
    data class Breed(val breed: String) : AccountDetailRowUIModel()

    data class Age(val ageInYears: Int) : AccountDetailRowUIModel()

    data class Size(val size: DogSize) : AccountDetailRowUIModel()

    data class Neighborhood(val neighborhood: String) : AccountDetailRowUIModel()

    data class FavoriteActivity(val activity: String) : AccountDetailRowUIModel()
}
