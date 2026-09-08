package com.perrystreet.woof.presentation.profile.uimodel

data class ProfileDetailsUIModel(
    val name: String,
    val summary: ProfileSummaryUIModel?,
    val heroTags: List<String>,
    val content: ProfileContentUIModel,
)
