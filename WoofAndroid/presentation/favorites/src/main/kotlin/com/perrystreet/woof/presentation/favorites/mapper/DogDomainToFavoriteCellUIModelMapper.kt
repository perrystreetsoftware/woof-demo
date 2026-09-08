package com.perrystreet.woof.presentation.favorites.mapper

import com.perrystreet.woof.models.dog.Dog
import com.perrystreet.woof.presentation.favorites.uimodel.FavoriteCellUIModel
import org.koin.core.annotation.Factory

@Factory
class DogDomainToFavoriteCellUIModelMapper {
    operator fun invoke(dog: Dog): FavoriteCellUIModel = FavoriteCellUIModel(
        id = dog.id,
        name = dog.name,
        photoUrl = dog.photoUrl,
        domain = dog,
    )
}
