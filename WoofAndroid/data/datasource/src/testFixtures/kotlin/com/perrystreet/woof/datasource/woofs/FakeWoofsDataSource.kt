package com.perrystreet.woof.datasource.woofs

import io.reactivex.rxjava3.core.Completable
import org.koin.core.annotation.Single

@Single
class FakeWoofsDataSource : IWoofsDataSource {
    val woofedDogIds = mutableListOf<Long>()
    var sendWoofError: Throwable? = null

    override fun sendWoof(dogId: Long): Completable =
        sendWoofError?.let { Completable.error(it) }
            ?: Completable.fromAction { woofedDogIds.add(dogId) }
}
