package com.perrystreet.woof.presentation.profile.uimodel

sealed class ProfileModerationDialogUIModel {
    abstract val name: String

    data class Report(override val name: String) : ProfileModerationDialogUIModel()

    data class Block(override val name: String) : ProfileModerationDialogUIModel()
}
