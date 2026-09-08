package com.perrystreet.woof.models.dog

data class DogsPage(
    val dogs: List<Dog>,
    val offset: Int,
    val total: Int,
)
