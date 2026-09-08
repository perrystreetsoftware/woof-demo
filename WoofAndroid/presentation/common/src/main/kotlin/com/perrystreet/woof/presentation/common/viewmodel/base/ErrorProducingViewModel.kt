package com.perrystreet.woof.presentation.common.viewmodel.base

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import java.util.Optional

open class ErrorProducingViewModel : BaseLifecycleViewModel() {
    protected val mutableError: BehaviorSubject<Optional<Throwable>> = BehaviorSubject.createDefault(Optional.empty())
    val error: Observable<Optional<Throwable>> = mutableError

    fun clearLastError() {
        mutableError.onNext(Optional.empty())
    }
}
