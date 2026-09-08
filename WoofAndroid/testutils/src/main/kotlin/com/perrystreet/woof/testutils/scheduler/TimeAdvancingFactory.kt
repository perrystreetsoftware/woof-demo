package com.perrystreet.woof.testutils.scheduler

import org.koin.test.KoinTest
import org.koin.test.inject
import java.util.concurrent.TimeUnit

class TimeAdvancingFactory : KoinTest {
    private val scheduler: TestSchedulerProvider by inject()

    fun withTestSchedulers() = apply {
        scheduler.useTestSchedulers()
    }

    fun tick() {
        scheduler.testScheduler.advanceTimeBy(1, TimeUnit.MICROSECONDS)
    }

    fun advanceMillis(amount: Long) {
        scheduler.testScheduler.advanceTimeBy(amount, TimeUnit.MILLISECONDS)
    }
}
