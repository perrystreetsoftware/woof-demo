package com.perrystreet.woof.presentation.profile.factory

import com.perrystreet.woof.datasource.dogs.DogsDataSourceFactory
import com.perrystreet.woof.repositories.dogs.DogsRepository
import org.koin.test.KoinTest
import org.koin.test.inject

class DogsFeedFactory : KoinTest {
    private val repository: DogsRepository by inject()

    fun withLoadedDogs(count: Int) = apply {
        DogsDataSourceFactory().withDogs(count)
        repository.getDogs(offset = 0, limit = count).ignoreElement().blockingAwait()
    }
}
