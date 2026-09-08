package com.perrystreet.woof.usecase.dogs

import com.perrystreet.woof.models.dog.Dog
import com.perrystreet.woof.models.dog.DogProfile
import com.perrystreet.woof.repositories.dogs.DogsRepository
import io.reactivex.rxjava3.core.Single
import org.koin.core.annotation.Factory

@Factory
class GetDogProfileUseCase(
    private val dogsRepository: DogsRepository,
) {
    operator fun invoke(dog: Dog): Single<DogProfile> = dogsRepository.getDogProfile(dog)
}
