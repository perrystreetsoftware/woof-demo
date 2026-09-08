package com.perrystreet.woof.testutils.rx

import io.reactivex.rxjava3.observers.TestObserver

fun <T : Any> TestObserver<T>.lastValue(): T = values().last()
