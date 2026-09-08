package com.perrystreet.woof.presentation.profile.uimodel

sealed class ProfileSectionUIModel {
    data class About(val name: String, val bio: String) : ProfileSectionUIModel()

    data class Personality(val tags: List<String>) : ProfileSectionUIModel()

    data class Details(val rows: List<ProfileDetailRowUIModel>) : ProfileSectionUIModel()
}
