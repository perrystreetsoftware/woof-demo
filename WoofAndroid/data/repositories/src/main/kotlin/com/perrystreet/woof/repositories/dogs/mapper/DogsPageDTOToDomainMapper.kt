package com.perrystreet.woof.repositories.dogs.mapper

import com.perrystreet.woof.dto.dog.DogsPageDTO
import com.perrystreet.woof.models.dog.DogsPage
import org.koin.core.annotation.Factory

@Factory
class DogsPageDTOToDomainMapper(
    private val dogMapper: DogDTOToDomainMapper,
) {
    operator fun invoke(dto: DogsPageDTO): DogsPage = DogsPage(
        dogs = dto.results.map { dogMapper(it) },
        offset = dto.offset,
        total = dto.total,
    )
}
