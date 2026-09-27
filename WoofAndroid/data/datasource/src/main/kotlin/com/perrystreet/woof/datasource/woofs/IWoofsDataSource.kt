package com.perrystreet.woof.datasource.woofs

import com.perrystreet.woof.dto.woof.ReceivedWoofDTO
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IWoofsDataSource {
    fun sendWoof(dogId: Long): Completable
    fun getReceivedWoofs(): Single<List<ReceivedWoofDTO>>
}
