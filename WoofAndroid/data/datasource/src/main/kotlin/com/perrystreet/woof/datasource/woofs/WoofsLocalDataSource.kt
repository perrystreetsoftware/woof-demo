package com.perrystreet.woof.datasource.woofs

import io.reactivex.rxjava3.core.Completable
import org.koin.core.annotation.Single

@Single
class WoofsLocalDataSource : IWoofsDataSource {
    private val woofedDogIds = mutableSetOf<Long>()

    override fun sendWoof(dogId: Long): Completable =
        Completable.fromAction { woofedDogIds.add(dogId) }
}
