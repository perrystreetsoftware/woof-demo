package com.perrystreet.woof.datasource.dogs

import com.perrystreet.woof.datasource.dogs.fixtures.DogFixtures
import com.perrystreet.woof.dto.dog.DogProfileDTO
import com.perrystreet.woof.dto.dog.DogsPageDTO
import com.perrystreet.woof.utils.scheduler.ISchedulerProvider
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit

@org.koin.core.annotation.Single
class DogsLocalDataSource(
    private val scheduler: ISchedulerProvider,
) : IDogsDataSource {

    override fun getDogs(offset: Int, limit: Int): Single<DogsPageDTO> =
        Single.fromCallable { DogFixtures.page(offset, limit) }
            .simulateNetworkLatency(GridLatencyMillis)

    override fun getDogProfile(dogId: Long): Single<DogProfileDTO> =
        Single.fromCallable { DogFixtures.profile(dogId) }
            .simulateNetworkLatency(ProfileLatencyMillis)

    private fun <T : Any> Single<T>.simulateNetworkLatency(millis: Long): Single<T> =
        delay(millis, TimeUnit.MILLISECONDS, scheduler.computationScheduler)
            .observeOn(scheduler.mainScheduler)

    private companion object {
        const val GridLatencyMillis = 1_200L
        const val ProfileLatencyMillis = 900L
    }
}
