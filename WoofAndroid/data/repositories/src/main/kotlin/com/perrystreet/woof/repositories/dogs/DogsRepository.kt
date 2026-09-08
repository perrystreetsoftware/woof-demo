package com.perrystreet.woof.repositories.dogs

import com.perrystreet.woof.datasource.dogs.IDogsDataSource
import com.perrystreet.woof.models.dog.Dog
import com.perrystreet.woof.models.dog.DogProfile
import com.perrystreet.woof.models.dog.DogsFeed
import com.perrystreet.woof.models.dog.DogsPage
import com.perrystreet.woof.repositories.dogs.mapper.DogProfileDTOToDomainMapper
import com.perrystreet.woof.repositories.dogs.mapper.DogsPageDTOToDomainMapper
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject

@org.koin.core.annotation.Single
class DogsRepository(
    private val dataSource: IDogsDataSource,
    private val pageMapper: DogsPageDTOToDomainMapper,
    private val profileMapper: DogProfileDTOToDomainMapper,
) {
    private val feed: BehaviorSubject<DogsFeed> = BehaviorSubject.createDefault(DogsFeed.Empty)
    private val profiles: BehaviorSubject<Map<Long, DogProfile>> = BehaviorSubject.createDefault(emptyMap())

    val dogsFeed: Observable<DogsFeed> = feed

    fun getDogs(offset: Int, limit: Int): Single<DogsPage> =
        dataSource.getDogs(offset, limit)
            .map { pageMapper(it) }
            .doOnSuccess { page -> feed.onNext(feed.value!!.append(page)) }

    fun getDogProfile(dog: Dog): Single<DogProfile> =
        profiles.value!![dog.id]?.let { Single.just(it) }
            ?: dataSource.getDogProfile(dog.id)
                .map { profileMapper(it) }
                .doOnSuccess { profile -> profiles.onNext(profiles.value!! + (dog.id to profile)) }

    private fun DogsFeed.append(page: DogsPage): DogsFeed {
        val knownIds = dogs.map { it.id }.toSet()
        val newDogs = page.dogs.filterNot { it.id in knownIds }
        return DogsFeed(dogs = dogs + newDogs, total = page.total)
    }
}
