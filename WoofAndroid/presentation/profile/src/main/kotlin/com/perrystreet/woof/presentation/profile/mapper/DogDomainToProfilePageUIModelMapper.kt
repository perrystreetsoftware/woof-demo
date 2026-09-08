package com.perrystreet.woof.presentation.profile.mapper

import com.perrystreet.woof.models.dog.Dog
import com.perrystreet.woof.presentation.profile.uimodel.ProfilePageUIModel
import org.koin.core.annotation.Factory

@Factory
class DogDomainToProfilePageUIModelMapper {
    operator fun invoke(dog: Dog): ProfilePageUIModel = ProfilePageUIModel(
        id = dog.id,
        name = dog.name,
        photoUrl = dog.photoUrl,
        domain = dog,
    )
}
