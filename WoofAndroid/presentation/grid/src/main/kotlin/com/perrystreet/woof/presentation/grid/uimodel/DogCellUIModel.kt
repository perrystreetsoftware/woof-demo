package com.perrystreet.woof.presentation.grid.uimodel

import com.perrystreet.woof.models.dog.Dog

data class DogCellUIModel(
    val index: Int,
    val id: Long,
    val name: String,
    val photoUrl: String,
    internal val domain: Dog,
)
