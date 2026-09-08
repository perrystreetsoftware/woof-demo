package com.perrystreet.woof.utils.scheduler

import io.reactivex.rxjava3.core.Scheduler

interface ISchedulerProvider {
    val mainScheduler: Scheduler
    val ioScheduler: Scheduler
    val computationScheduler: Scheduler
}
