package com.perrystreet.woof.dto.dog

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DogProfileDTO(
    @Json(name = "dog") val dog: DogDTO,
    @Json(name = "breed") val breed: String,
    @Json(name = "age_in_years") val ageInYears: Int,
    @Json(name = "size") val size: String,
    @Json(name = "neighborhood") val neighborhood: String,
    @Json(name = "personality") val personality: List<String> = emptyList(),
    @Json(name = "favorite_activity") val favoriteActivity: String,
    @Json(name = "bio") val bio: String,
)
