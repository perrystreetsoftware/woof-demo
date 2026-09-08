package com.perrystreet.woof.presentation.profile.factory

import com.perrystreet.woof.models.dog.Dog

class DogFactory {
    private var dog = Dog(id = 1, name = "Bruno", photoUrl = "file:///android_asset/dogs/golden_retriever_01.jpg")

    fun withId(id: Long) = apply {
        dog = dog.copy(id = id, name = "Dog $id")
    }

    fun produce(): Dog = dog
}
