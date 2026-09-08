package com.perrystreet.woof.datasource.account

import com.perrystreet.woof.datasource.dogs.fixtures.DogFixtures
import com.perrystreet.woof.dto.dog.DogProfileDTO
import com.perrystreet.woof.utils.scheduler.ISchedulerProvider
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit

@org.koin.core.annotation.Single
class AccountLocalDataSource(
    private val scheduler: ISchedulerProvider,
) : IAccountDataSource {

    override fun getAccount(): Single<DogProfileDTO> =
        Single.fromCallable { DogFixtures.account() }
            .delay(LatencyMillis, TimeUnit.MILLISECONDS, scheduler.computationScheduler)
            .observeOn(scheduler.mainScheduler)

    private companion object {
        const val LatencyMillis = 600L
    }
}
