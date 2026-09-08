package com.perrystreet.woof.datasource.moderation

import io.reactivex.rxjava3.core.Completable

interface IModerationDataSource {
    fun reportDog(dogId: Long): Completable
    fun blockDog(dogId: Long): Completable
}
