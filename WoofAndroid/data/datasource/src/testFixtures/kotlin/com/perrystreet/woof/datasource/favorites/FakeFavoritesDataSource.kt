package com.perrystreet.woof.datasource.favorites

import io.reactivex.rxjava3.core.Completable
import org.koin.core.annotation.Single

@Single
class FakeFavoritesDataSource : IFavoritesDataSource {
    val favoriteDogIds = mutableSetOf<Long>()
    var addFavoriteError: Throwable? = null

    override fun addFavorite(dogId: Long): Completable =
        addFavoriteError?.let { Completable.error(it) }
            ?: Completable.fromAction { favoriteDogIds.add(dogId) }

    override fun removeFavorite(dogId: Long): Completable =
        Completable.fromAction { favoriteDogIds.remove(dogId) }
}
