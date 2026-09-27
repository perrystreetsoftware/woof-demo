package com.perrystreet.woof.presentation.browse.mapper

import com.perrystreet.woof.models.dog.Dog
import com.perrystreet.woof.presentation.browse.uimodel.DogCellUIModel
import org.koin.core.annotation.Factory

@Factory
class DogDomainToCellUIModelMapper {
    operator fun invoke(dog: Dog, index: Int): DogCellUIModel = DogCellUIModel(
        index = index,
        id = dog.id,
        name = dog.name,
        photoUrl = dog.photoUrl,
        domain = dog,
    )
}
