package com.perrystreet.woof.dto.dog

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DogsPageDTO(
    @Json(name = "results") val results: List<DogDTO> = emptyList(),
    @Json(name = "offset") val offset: Int,
    @Json(name = "total") val total: Int,
)
