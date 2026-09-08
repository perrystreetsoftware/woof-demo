package com.perrystreet.woof.models.dog

data class DogProfile(
    val dog: Dog,
    val breed: String,
    val ageInYears: Int,
    val size: DogSize,
    val neighborhood: String,
    val personality: List<String>,
    val favoriteActivity: String,
    val bio: String,
)
