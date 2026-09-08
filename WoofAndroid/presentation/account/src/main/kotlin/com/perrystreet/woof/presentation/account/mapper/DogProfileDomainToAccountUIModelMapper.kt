package com.perrystreet.woof.presentation.account.mapper

import com.perrystreet.woof.models.dog.DogProfile
import com.perrystreet.woof.presentation.account.uimodel.AccountDetailRowUIModel
import com.perrystreet.woof.presentation.account.uimodel.AccountSummaryUIModel
import com.perrystreet.woof.presentation.account.uimodel.AccountUIModel
import org.koin.core.annotation.Factory

@Factory
class DogProfileDomainToAccountUIModelMapper {
    operator fun invoke(profile: DogProfile): AccountUIModel = AccountUIModel(
        name = profile.dog.name,
        photoUrl = profile.dog.photoUrl,
        summary = AccountSummaryUIModel(
            ageInYears = profile.ageInYears,
            breed = profile.breed,
            neighborhood = profile.neighborhood,
        ),
        bio = profile.bio,
        personality = profile.personality,
        rows = listOf(
            AccountDetailRowUIModel.Breed(profile.breed),
            AccountDetailRowUIModel.Age(profile.ageInYears),
            AccountDetailRowUIModel.Size(profile.size),
            AccountDetailRowUIModel.Neighborhood(profile.neighborhood),
            AccountDetailRowUIModel.FavoriteActivity(profile.favoriteActivity),
        ),
    )
}
