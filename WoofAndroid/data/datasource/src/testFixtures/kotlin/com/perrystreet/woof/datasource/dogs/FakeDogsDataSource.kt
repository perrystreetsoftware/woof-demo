package com.perrystreet.woof.datasource.dogs

import com.perrystreet.woof.dto.dog.DogDTO
import com.perrystreet.woof.dto.dog.DogProfileDTO
import com.perrystreet.woof.dto.dog.DogSizeDTO
import com.perrystreet.woof.dto.dog.DogsPageDTO
import io.reactivex.rxjava3.core.Single

@org.koin.core.annotation.Single
class FakeDogsDataSource : IDogsDataSource {
    var dogs: List<DogDTO> = emptyList()
    var getDogsError: Throwable? = null
    var getDogsCount: Int = 0
    var getDogProfileError: Throwable? = null
    var getDogProfileCount: Int = 0

    override fun getDogs(offset: Int, limit: Int): Single<DogsPageDTO> {
        getDogsCount++
        return getDogsError?.let { Single.error(it) }
            ?: Single.just(
                DogsPageDTO(results = dogs.drop(offset).take(limit), offset = offset, total = dogs.size),
            )
    }

    override fun getDogProfile(dogId: Long): Single<DogProfileDTO> {
        getDogProfileCount++
        return getDogProfileError?.let { Single.error(it) }
            ?: Single.just(profileOf(dogs.first { it.id == dogId }))
    }

    private fun profileOf(dog: DogDTO) = DogProfileDTO(
        dog = dog,
        breed = "Golden Retriever",
        ageInYears = 4,
        size = DogSizeDTO.Large,
        neighborhood = "Kolonaki, Athens",
        personality = listOf("Playful", "Cuddly"),
        favoriteActivity = "Swimming at the beach",
        bio = "Loves swimming, tennis balls, and stealing snacks.",
    )
}
