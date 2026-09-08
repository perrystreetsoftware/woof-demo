package com.perrystreet.woof.presentation.common.viewmodel.base

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseLifecycleViewModel : ViewModel() {
    protected val disposables = CompositeDisposable()
    private var didViewAppear = false

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

    fun onViewAppear() {
        if (!didViewAppear) {
            onFirstAppear()
            didViewAppear = true
        }
        onEveryAppear()
    }

    protected open fun onFirstAppear() = Unit

    protected open fun onEveryAppear() = Unit
}
