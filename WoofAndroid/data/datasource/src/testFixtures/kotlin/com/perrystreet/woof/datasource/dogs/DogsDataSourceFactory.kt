package com.perrystreet.woof.datasource.dogs

import com.perrystreet.woof.dto.dog.DogDTO
import org.koin.test.KoinTest
import org.koin.test.inject

class DogsDataSourceFactory : KoinTest {
    private val dataSource: FakeDogsDataSource by inject()

    fun withDogs(count: Int) = apply {
        dataSource.dogs = (1..count).map { index ->
            DogDTO(id = index.toLong(), name = "Dog $index", photoUrl = "file:///android_asset/dogs/golden_retriever_01.jpg")
        }
    }

    fun withDogsError() = apply {
        dataSource.getDogsError = IllegalStateException("Could not load dogs")
    }

    fun withoutErrors() = apply {
        dataSource.getDogsError = null
        dataSource.getDogProfileError = null
    }

    fun withDogProfileError() = apply {
        dataSource.getDogProfileError = IllegalStateException("Could not load profile")
    }
}
