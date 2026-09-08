package com.perrystreet.woof.repositories.dogs.mapper

import com.perrystreet.woof.dto.dog.DogDTO
import com.perrystreet.woof.models.dog.Dog
import org.koin.core.annotation.Factory

@Factory
class DogDTOToDomainMapper {
    operator fun invoke(dto: DogDTO): Dog = Dog(
        id = dto.id,
        name = dto.name,
        photoUrl = dto.photoUrl,
    )
}
