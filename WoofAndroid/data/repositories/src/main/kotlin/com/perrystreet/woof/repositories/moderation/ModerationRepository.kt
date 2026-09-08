package com.perrystreet.woof.repositories.moderation

import com.perrystreet.woof.datasource.moderation.IModerationDataSource
import com.perrystreet.woof.models.dog.Dog
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.subjects.BehaviorSubject
import org.koin.core.annotation.Single

@Single
class ModerationRepository(
    private val dataSource: IModerationDataSource,
) {
    private val disposables = CompositeDisposable()
    private val blocks: BehaviorSubject<Set<Long>> = BehaviorSubject.createDefault(emptySet())

    val blockedDogIds: Observable<Set<Long>> = blocks

    fun addReport(dog: Dog): Completable {
        val stream = dataSource.reportDog(dog.id).cache()
        disposables += stream.subscribe({}, {})
        return stream
    }

    fun addBlock(dog: Dog): Completable {
        val stream = dataSource.blockDog(dog.id)
            .doOnComplete { blocks.onNext(blocks.value!! + dog.id) }
            .cache()
        disposables += stream.subscribe({}, {})
        return stream
    }
}
