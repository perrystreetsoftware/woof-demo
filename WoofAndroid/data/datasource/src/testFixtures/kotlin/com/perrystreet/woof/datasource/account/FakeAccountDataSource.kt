package com.perrystreet.woof.datasource.account

import com.perrystreet.woof.dto.dog.DogDTO
import com.perrystreet.woof.dto.dog.DogProfileDTO
import com.perrystreet.woof.dto.dog.DogSizeDTO
import io.reactivex.rxjava3.core.Single

@org.koin.core.annotation.Single
class FakeAccountDataSource : IAccountDataSource {
    var account: DogProfileDTO = DogProfileDTO(
        dog = DogDTO(id = 0, name = "Milo", photoUrl = "file:///android_asset/dogs/border_collie_01.jpg"),
        breed = "Border Collie",
        ageInYears = 3,
        size = DogSizeDTO.Medium,
        neighborhood = "Schöneberg, Berlin",
        personality = listOf("Frisbee pro", "Early riser"),
        favoriteActivity = "Agility courses",
        bio = "Herds tennis balls for a living.",
    )
    var getAccountError: Throwable? = null

    override fun getAccount(): Single<DogProfileDTO> =
        getAccountError?.let { Single.error(it) } ?: Single.just(account)
}
