package com.perrystreet.woof.presentation.profile.ui.preview

import com.perrystreet.woof.models.dog.Dog
import com.perrystreet.woof.models.dog.DogSize
import com.perrystreet.woof.presentation.profile.uimodel.ProfileContentUIModel
import com.perrystreet.woof.presentation.profile.uimodel.ProfileDetailRowUIModel
import com.perrystreet.woof.presentation.profile.uimodel.ProfileDetailsUIModel
import com.perrystreet.woof.presentation.profile.uimodel.ProfileOverflowMenuItemUIModel
import com.perrystreet.woof.presentation.profile.uimodel.ProfilePageUIModel
import com.perrystreet.woof.presentation.profile.uimodel.ProfileSectionUIModel
import com.perrystreet.woof.presentation.profile.uimodel.ProfileSummaryUIModel
import com.perrystreet.woof.presentation.profile.viewmodel.ProfileHeaderViewModel

internal object ProfilePreviewData {
    private val dog = Dog(id = 1, name = "Bruno", photoUrl = "")

    fun page() = ProfilePageUIModel(id = dog.id, name = dog.name, photoUrl = dog.photoUrl, domain = dog)

    fun header() = ProfileHeaderViewModel.State(
        name = dog.name,
        isFavorite = true,
        isOverflowExpanded = false,
        overflowItems = ProfileOverflowMenuItemUIModel.entries,
    )

    fun loadingDetails() = ProfileDetailsUIModel(
        name = dog.name,
        summary = null,
        heroTags = emptyList(),
        content = ProfileContentUIModel.Loading,
    )

    fun details() = ProfileDetailsUIModel(
        name = dog.name,
        summary = ProfileSummaryUIModel(ageInYears = 4, breed = "Golden Retriever", neighborhood = "Kolonaki, Athens"),
        heroTags = listOf("Playful", "Water lover", "Snack enthusiast"),
        content = ProfileContentUIModel.Visible(
            sections = listOf(
                ProfileSectionUIModel.About(name = dog.name, bio = "Loves swimming, tennis balls, and stealing snacks."),
                ProfileSectionUIModel.Personality(tags = listOf("Playful", "Water lover", "Snack enthusiast", "Goofy")),
                ProfileSectionUIModel.Details(
                    rows = listOf(
                        ProfileDetailRowUIModel.Breed("Golden Retriever"),
                        ProfileDetailRowUIModel.Age(4),
                        ProfileDetailRowUIModel.Size(DogSize.Large),
                        ProfileDetailRowUIModel.Neighborhood("Kolonaki, Athens"),
                        ProfileDetailRowUIModel.FavoriteActivity("Swimming at the beach"),
                    ),
                ),
            ),
        ),
    )
}
