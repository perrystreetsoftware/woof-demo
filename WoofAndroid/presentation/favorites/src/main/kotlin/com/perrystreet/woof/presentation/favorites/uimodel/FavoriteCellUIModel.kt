package com.perrystreet.woof.presentation.favorites.uimodel

import com.perrystreet.woof.models.dog.Dog

data class FavoriteCellUIModel(
    val id: Long,
    val name: String,
    val photoUrl: String,
    internal val domain: Dog,
)
