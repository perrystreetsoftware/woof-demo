package com.perrystreet.woof.datasource.woofs

import io.reactivex.rxjava3.core.Completable

interface IWoofsDataSource {
    fun sendWoof(dogId: Long): Completable
}
