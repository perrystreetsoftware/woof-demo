package com.perrystreet.woof.presentation.common.rx

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rxjava3.subscribeAsState
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

class UiObservable<T : Any>(
    val observable: Observable<T>,
    private val getCachedValue: () -> T,
) {
    @Composable
    fun subscribeAsState(): State<T> = observable.subscribeAsState(initial = getCachedValue())

    companion object {
        fun <T : Any> Observable<T>.asUiObservable(initialValue: T): UiObservable<T> {
            val cache = DerivedStateCache(initialValue, this)
            return UiObservable(cache.observable, cache::latestValue)
        }

        fun <T : Any> BehaviorSubject<T>.asUiObservable(): UiObservable<T> =
            UiObservable(this) { this.value!! }
    }
}
