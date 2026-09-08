package com.perrystreet.woof.usecase.woofs

import com.perrystreet.woof.models.dog.Dog
import com.perrystreet.woof.repositories.woofs.WoofsRepository
import io.reactivex.rxjava3.core.Observable
import org.koin.core.annotation.Factory

@Factory
class HasWoofedDogUseCase(
    private val woofsRepository: WoofsRepository,
) {
    operator fun invoke(dog: Dog): Observable<Boolean> =
        woofsRepository.woofedDogIds
            .map { dog.id in it }
            .distinctUntilChanged()
}
