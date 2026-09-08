package com.perrystreet.woof.repositories.account

import com.perrystreet.woof.datasource.account.IAccountDataSource
import com.perrystreet.woof.models.dog.DogProfile
import com.perrystreet.woof.repositories.dogs.mapper.DogProfileDTOToDomainMapper
import io.reactivex.rxjava3.core.Single

@org.koin.core.annotation.Single
class AccountRepository(
    private val dataSource: IAccountDataSource,
    private val profileMapper: DogProfileDTOToDomainMapper,
) {
    fun getAccount(): Single<DogProfile> = dataSource.getAccount().map { profileMapper(it) }
}
