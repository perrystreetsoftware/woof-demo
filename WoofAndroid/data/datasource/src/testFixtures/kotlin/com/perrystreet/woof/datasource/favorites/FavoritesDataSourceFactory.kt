package com.perrystreet.woof.datasource.favorites

import org.koin.test.KoinTest
import org.koin.test.inject

class FavoritesDataSourceFactory : KoinTest {
    private val dataSource: FakeFavoritesDataSource by inject()

    fun withAddFavoriteError() = apply {
        dataSource.addFavoriteError = IllegalStateException("Could not add favorite")
    }
}
