package com.perrystreet.woof.datasource.woofs

import com.perrystreet.woof.datasource.dogs.fixtures.DogFixtures
import com.perrystreet.woof.dto.woof.ReceivedWoofDTO
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit

@org.koin.core.annotation.Single
class WoofsLocalDataSource : IWoofsDataSource {
    private val woofedDogIds = WoofedBackDogIds.toMutableSet()

    override fun sendWoof(dogId: Long): Completable =
        Completable.fromAction { woofedDogIds.add(dogId) }

    override fun getReceivedWoofs(): Single<List<ReceivedWoofDTO>> =
        Single.fromCallable {
            val now = System.currentTimeMillis()
            ReceivedWoofs.map { (dogId, minutesAgo) ->
                ReceivedWoofDTO(
                    dog = DogFixtures.profile(dogId).dog,
                    woofedAtMillis = now - TimeUnit.MINUTES.toMillis(minutesAgo),
                    woofedBack = dogId in woofedDogIds,
                )
            }
        }

    private companion object {
        val ReceivedWoofs = listOf(1L to 5L, 2L to 120L, 3L to 1_440L, 4L to 4_320L)
        val WoofedBackDogIds = setOf(2L, 4L)
    }
}
