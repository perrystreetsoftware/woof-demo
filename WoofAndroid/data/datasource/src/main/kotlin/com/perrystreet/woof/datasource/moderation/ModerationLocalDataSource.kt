package com.perrystreet.woof.datasource.moderation

import io.reactivex.rxjava3.core.Completable
import org.koin.core.annotation.Single

@Single
class ModerationLocalDataSource : IModerationDataSource {
    private val reportedDogIds = mutableSetOf<Long>()
    private val blockedDogIds = mutableSetOf<Long>()

    override fun reportDog(dogId: Long): Completable =
        Completable.fromAction { reportedDogIds.add(dogId) }

    override fun blockDog(dogId: Long): Completable =
        Completable.fromAction { blockedDogIds.add(dogId) }
}
