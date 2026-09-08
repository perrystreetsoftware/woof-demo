package com.perrystreet.woof.datasource.favorites

import io.reactivex.rxjava3.core.Completable
import org.koin.core.annotation.Single

@Single
class FavoritesLocalDataSource : IFavoritesDataSource {
    private val favoriteDogIds = mutableSetOf<Long>()

    override fun addFavorite(dogId: Long): Completable =
        Completable.fromAction { favoriteDogIds.add(dogId) }

    override fun removeFavorite(dogId: Long): Completable =
        Completable.fromAction { favoriteDogIds.remove(dogId) }
}
