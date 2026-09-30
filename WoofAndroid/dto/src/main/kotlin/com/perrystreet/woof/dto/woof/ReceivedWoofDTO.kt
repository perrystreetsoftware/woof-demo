package com.perrystreet.woof.dto.woof

import com.perrystreet.woof.dto.dog.DogDTO
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReceivedWoofDTO(
    @Json(name = "dog") val dog: DogDTO,
    @Json(name = "woofed_at") val woofedAtMillis: Long,
    @Json(name = "woofed_back") val woofedBack: Boolean,
)
