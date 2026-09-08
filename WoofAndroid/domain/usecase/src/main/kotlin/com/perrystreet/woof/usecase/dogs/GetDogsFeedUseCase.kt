package com.perrystreet.woof.usecase.dogs

import com.perrystreet.woof.models.dog.DogsFeed
import com.perrystreet.woof.repositories.dogs.DogsRepository
import com.perrystreet.woof.repositories.moderation.ModerationRepository
import io.reactivex.rxjava3.core.Observable
import org.koin.core.annotation.Factory

@Factory
class GetDogsFeedUseCase(
    private val dogsRepository: DogsRepository,
    private val moderationRepository: ModerationRepository,
) {
    operator fun invoke(): Observable<DogsFeed> =
        Observable.combineLatest(
            dogsRepository.dogsFeed,
            moderationRepository.blockedDogIds,
        ) { feed, blockedIds ->
            DogsFeed(
                dogs = feed.dogs.filterNot { it.id in blockedIds },
                total = feed.total - blockedIds.size,
            )
        }.distinctUntilChanged()
}
