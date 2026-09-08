package com.perrystreet.woof.presentation.profile.uimodel

sealed class ProfileToastUIModel {
    data class WoofSent(val name: String) : ProfileToastUIModel()

    data class MessageSent(val name: String) : ProfileToastUIModel()

    data object ReportSent : ProfileToastUIModel()
}
