package com.perrystreet.woof.usecase.favorites

import com.perrystreet.woof.models.dog.Dog
import com.perrystreet.woof.repositories.favorites.FavoritesRepository
import io.reactivex.rxjava3.core.Observable
import org.koin.core.annotation.Factory

@Factory
class IsDogFavoriteUseCase(
    private val favoritesRepository: FavoritesRepository,
) {
    operator fun invoke(dog: Dog): Observable<Boolean> =
        favoritesRepository.favoriteDogIds
            .map { dog.id in it }
            .distinctUntilChanged()
}
