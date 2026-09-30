package com.perrystreet.woof.datasource.woofs

import com.perrystreet.woof.dto.woof.ReceivedWoofDTO
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@org.koin.core.annotation.Single
class FakeWoofsDataSource : IWoofsDataSource {
    val woofedDogIds = mutableListOf<Long>()
    val receivedWoofs = mutableListOf<ReceivedWoofDTO>()
    var sendWoofError: Throwable? = null

    override fun sendWoof(dogId: Long): Completable =
        sendWoofError?.let { Completable.error(it) }
            ?: Completable.fromAction { woofedDogIds.add(dogId) }

    override fun getReceivedWoofs(): Single<List<ReceivedWoofDTO>> =
        Single.fromCallable { receivedWoofs.toList() }
}
