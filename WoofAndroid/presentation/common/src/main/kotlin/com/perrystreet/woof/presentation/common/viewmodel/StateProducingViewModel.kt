package com.perrystreet.woof.presentation.common.viewmodel

import com.perrystreet.woof.presentation.common.rx.UiObservable
import com.perrystreet.woof.presentation.common.rx.UiObservable.Companion.asUiObservable
import com.perrystreet.woof.presentation.common.viewmodel.base.ErrorProducingViewModel
import com.perrystreet.woof.presentation.common.viewmodel.base.IStatefulViewModel
import io.reactivex.rxjava3.subjects.BehaviorSubject

open class StateProducingViewModel<T : Any>(initialValue: T) : ErrorProducingViewModel(), IStatefulViewModel<T> {
    protected val _state: BehaviorSubject<T> = BehaviorSubject.createDefault(initialValue)
    override val state: UiObservable<T> = _state.asUiObservable()

    val currentState: T
        get() = _state.value!!
}
