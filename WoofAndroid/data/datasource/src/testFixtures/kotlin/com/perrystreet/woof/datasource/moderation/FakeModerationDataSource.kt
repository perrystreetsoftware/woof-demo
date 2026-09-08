package com.perrystreet.woof.datasource.moderation

import io.reactivex.rxjava3.core.Completable
import org.koin.core.annotation.Single

@Single
class FakeModerationDataSource : IModerationDataSource {
    val reportedDogIds = mutableListOf<Long>()
    val blockedDogIds = mutableListOf<Long>()
    var blockDogError: Throwable? = null

    override fun reportDog(dogId: Long): Completable =
        Completable.fromAction { reportedDogIds.add(dogId) }

    override fun blockDog(dogId: Long): Completable =
        blockDogError?.let { Completable.error(it) }
            ?: Completable.fromAction { blockedDogIds.add(dogId) }
}
