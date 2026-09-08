package com.perrystreet.woof.datasource.dogs

import com.perrystreet.woof.dto.dog.DogProfileDTO
import com.perrystreet.woof.dto.dog.DogsPageDTO
import io.reactivex.rxjava3.core.Single

interface IDogsDataSource {
    fun getDogs(offset: Int, limit: Int): Single<DogsPageDTO>
    fun getDogProfile(dogId: Long): Single<DogProfileDTO>
}
