package com.perrystreet.woof.dto.dog

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DogDTO(
    @Json(name = "id") val id: Long,
    @Json(name = "name") val name: String,
    @Json(name = "photo_url") val photoUrl: String,
)
