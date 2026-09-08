package com.perrystreet.woof.presentation.profile.uimodel

sealed class ProfileContentUIModel {
    data object Loading : ProfileContentUIModel()

    data class Visible(val sections: List<ProfileSectionUIModel>) : ProfileContentUIModel()
}
