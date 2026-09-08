package com.perrystreet.woof.presentation.profile.mapper

import com.perrystreet.woof.models.dog.DogProfile
import com.perrystreet.woof.presentation.profile.uimodel.ProfileContentUIModel
import com.perrystreet.woof.presentation.profile.uimodel.ProfileDetailRowUIModel
import com.perrystreet.woof.presentation.profile.uimodel.ProfileDetailsUIModel
import com.perrystreet.woof.presentation.profile.uimodel.ProfileSectionUIModel
import com.perrystreet.woof.presentation.profile.uimodel.ProfileSummaryUIModel
import org.koin.core.annotation.Factory

@Factory
class DogProfileDomainToUIModelMapper {
    operator fun invoke(profile: DogProfile): ProfileDetailsUIModel = ProfileDetailsUIModel(
        name = profile.dog.name,
        summary = ProfileSummaryUIModel(
            ageInYears = profile.ageInYears,
            breed = profile.breed,
            neighborhood = profile.neighborhood,
        ),
        heroTags = profile.personality.take(HeroTagCount),
        content = ProfileContentUIModel.Visible(
            sections = listOf(
                ProfileSectionUIModel.About(name = profile.dog.name, bio = profile.bio),
                ProfileSectionUIModel.Personality(tags = profile.personality),
                ProfileSectionUIModel.Details(
                    rows = listOf(
                        ProfileDetailRowUIModel.Breed(profile.breed),
                        ProfileDetailRowUIModel.Age(profile.ageInYears),
                        ProfileDetailRowUIModel.Size(profile.size),
                        ProfileDetailRowUIModel.Neighborhood(profile.neighborhood),
                        ProfileDetailRowUIModel.FavoriteActivity(profile.favoriteActivity),
                    ),
                ),
            ),
        ),
    )

    private companion object {
        const val HeroTagCount = 3
    }
}
