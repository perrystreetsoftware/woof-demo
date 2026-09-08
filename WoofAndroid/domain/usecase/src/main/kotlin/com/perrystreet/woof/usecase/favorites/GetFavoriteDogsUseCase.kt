package com.perrystreet.woof.usecase.favorites

import com.perrystreet.woof.models.dog.Dog
import com.perrystreet.woof.repositories.favorites.FavoritesRepository
import com.perrystreet.woof.usecase.dogs.GetDogsFeedUseCase
import io.reactivex.rxjava3.core.Observable
import org.koin.core.annotation.Factory

@Factory
class GetFavoriteDogsUseCase(
    private val getDogsFeedUseCase: GetDogsFeedUseCase,
    private val favoritesRepository: FavoritesRepository,
) {
    operator fun invoke(): Observable<List<Dog>> =
        Observable.combineLatest(
            getDogsFeedUseCase(),
            favoritesRepository.favoriteDogIds,
        ) { feed, favoriteIds ->
            feed.dogs.filter { it.id in favoriteIds }
        }.distinctUntilChanged()
}
