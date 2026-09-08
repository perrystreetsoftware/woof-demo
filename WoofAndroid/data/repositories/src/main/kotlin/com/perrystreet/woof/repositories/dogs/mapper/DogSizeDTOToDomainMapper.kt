package com.perrystreet.woof.repositories.dogs.mapper

import com.perrystreet.woof.dto.dog.DogSizeDTO
import com.perrystreet.woof.models.dog.DogSize
import org.koin.core.annotation.Factory

@Factory
class DogSizeDTOToDomainMapper {
    operator fun invoke(size: String): DogSize = when (size) {
        DogSizeDTO.Small -> DogSize.Small
        DogSizeDTO.Large -> DogSize.Large
        else -> DogSize.Medium
    }
}
