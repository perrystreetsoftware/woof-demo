package com.perrystreet.woof.usecase.dogs

import com.perrystreet.woof.models.dog.DogsPage
import com.perrystreet.woof.repositories.dogs.DogsRepository
import io.reactivex.rxjava3.core.Single
import org.koin.core.annotation.Factory

@Factory
class LoadNextDogsPageUseCase(
    private val dogsRepository: DogsRepository,
) {
    operator fun invoke(): Single<DogsPage> =
        dogsRepository.dogsFeed
            .firstOrError()
            .flatMap { feed -> dogsRepository.getDogs(offset = feed.dogs.size, limit = PageSize) }

    companion object {
        const val PageSize = 200
    }
}
