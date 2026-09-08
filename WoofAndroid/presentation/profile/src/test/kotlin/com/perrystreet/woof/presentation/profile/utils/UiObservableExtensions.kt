package com.perrystreet.woof.presentation.profile.utils

import com.perrystreet.woof.presentation.common.rx.UiObservable
import io.reactivex.rxjava3.observers.TestObserver

internal object UiObservableExtensions {
    fun <T : Any> UiObservable<T>.test(): TestObserver<T> = observable.test()
}
