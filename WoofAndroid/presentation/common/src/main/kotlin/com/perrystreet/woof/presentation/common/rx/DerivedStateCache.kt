package com.perrystreet.woof.presentation.common.rx

import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.atomic.AtomicReference

class DerivedStateCache<T : Any>(
    initialValue: T,
    sourceObservable: Observable<T>,
) {
    private val atomicValue: AtomicReference<T> = AtomicReference(initialValue)

    val observable: Observable<T> = sourceObservable.doOnNext { atomicValue.set(it) }

    fun latestValue(): T = atomicValue.get()
}
