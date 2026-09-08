package com.perrystreet.woof.repositories.woofs

import com.perrystreet.woof.datasource.woofs.IWoofsDataSource
import com.perrystreet.woof.models.dog.Dog
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.subjects.BehaviorSubject
import org.koin.core.annotation.Single

@Single
class WoofsRepository(
    private val dataSource: IWoofsDataSource,
) {
    private val disposables = CompositeDisposable()
    private val woofs: BehaviorSubject<Set<Long>> = BehaviorSubject.createDefault(emptySet())

    val woofedDogIds: Observable<Set<Long>> = woofs

    fun addWoof(dog: Dog): Completable {
        val stream = dataSource.sendWoof(dog.id)
            .doOnComplete { woofs.onNext(woofs.value!! + dog.id) }
            .cache()
        disposables += stream.subscribe({}, {})
        return stream
    }
}
