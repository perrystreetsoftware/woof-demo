package com.perrystreet.woof.presentation.profile.uimodel

import com.perrystreet.woof.models.dog.Dog

data class ProfilePageUIModel(
    val id: Long,
    val name: String,
    val photoUrl: String,
    internal val domain: Dog,
)
