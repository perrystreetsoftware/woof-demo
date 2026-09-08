package com.perrystreet.woof.datasource.account

import com.perrystreet.woof.dto.dog.DogProfileDTO
import io.reactivex.rxjava3.core.Single

interface IAccountDataSource {
    fun getAccount(): Single<DogProfileDTO>
}
