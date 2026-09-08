package com.perrystreet.woof.usecase.favorites

import com.perrystreet.woof.models.dog.Dog
import com.perrystreet.woof.repositories.favorites.FavoritesRepository
import io.reactivex.rxjava3.core.Completable
import org.koin.core.annotation.Factory

@Factory
class ToggleDogFavoriteUseCase(
    private val favoritesRepository: FavoritesRepository,
) {
    operator fun invoke(dog: Dog): Completable =
        favoritesRepository.favoriteDogIds
            .firstOrError()
            .flatMapCompletable { favoriteIds ->
                when (dog.id in favoriteIds) {
                    true -> favoritesRepository.removeFavorite(dog)
                    false -> favoritesRepository.addFavorite(dog)
                }
            }
}
