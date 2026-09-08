package com.perrystreet.woof.models.dog

data class DogsFeed(
    val dogs: List<Dog>,
    val total: Int,
) {
    val hasMore: Boolean
        get() = dogs.size < total

    companion object {
        val Empty = DogsFeed(dogs = emptyList(), total = 0)
    }
}
