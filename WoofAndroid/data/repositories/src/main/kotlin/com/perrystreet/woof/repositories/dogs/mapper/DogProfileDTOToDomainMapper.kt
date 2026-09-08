package com.perrystreet.woof.repositories.dogs.mapper

import com.perrystreet.woof.dto.dog.DogProfileDTO
import com.perrystreet.woof.models.dog.DogProfile
import org.koin.core.annotation.Factory

@Factory
class DogProfileDTOToDomainMapper(
    private val dogMapper: DogDTOToDomainMapper,
    private val sizeMapper: DogSizeDTOToDomainMapper,
) {
    operator fun invoke(dto: DogProfileDTO): DogProfile = DogProfile(
        dog = dogMapper(dto.dog),
        breed = dto.breed,
        ageInYears = dto.ageInYears,
        size = sizeMapper(dto.size),
        neighborhood = dto.neighborhood,
        personality = dto.personality,
        favoriteActivity = dto.favoriteActivity,
        bio = dto.bio,
    )
}
