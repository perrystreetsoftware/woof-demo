package com.perrystreet.woof.presentation.common.viewmodel.base

import com.perrystreet.woof.presentation.common.rx.UiObservable

interface IStatefulViewModel<T : Any> {
    val state: UiObservable<T>
}
