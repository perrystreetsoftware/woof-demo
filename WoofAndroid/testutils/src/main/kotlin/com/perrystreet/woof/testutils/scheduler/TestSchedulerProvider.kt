package com.perrystreet.woof.testutils.scheduler

import com.perrystreet.woof.utils.scheduler.ISchedulerProvider
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.schedulers.TestScheduler

class TestSchedulerProvider : ISchedulerProvider {
    val testScheduler = TestScheduler()

    override var mainScheduler: Scheduler = Schedulers.trampoline()
    override var ioScheduler: Scheduler = Schedulers.trampoline()
    override var computationScheduler: Scheduler = Schedulers.trampoline()

    fun useTestSchedulers() {
        mainScheduler = testScheduler
        ioScheduler = testScheduler
        computationScheduler = testScheduler
    }
}
