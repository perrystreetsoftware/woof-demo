package com.perrystreet.woof.datasource.favorites

import io.reactivex.rxjava3.core.Completable

interface IFavoritesDataSource {
    fun addFavorite(dogId: Long): Completable
    fun removeFavorite(dogId: Long): Completable
}
