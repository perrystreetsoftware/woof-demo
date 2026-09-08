package com.perrystreet.woof.usecase.woofs

import com.perrystreet.woof.models.dog.Dog
import com.perrystreet.woof.models.errors.WoofException
import com.perrystreet.woof.repositories.woofs.WoofsRepository
import io.reactivex.rxjava3.core.Completable
import org.koin.core.annotation.Factory

@Factory
class SendWoofUseCase(
    private val woofsRepository: WoofsRepository,
) {
    operator fun invoke(dog: Dog): Completable =
        woofsRepository.woofedDogIds
            .firstOrError()
            .flatMapCompletable { woofedIds ->
                when (dog.id in woofedIds) {
                    true -> Completable.error(WoofException.AlreadyWoofed)
                    false -> woofsRepository.addWoof(dog)
                }
            }
}
