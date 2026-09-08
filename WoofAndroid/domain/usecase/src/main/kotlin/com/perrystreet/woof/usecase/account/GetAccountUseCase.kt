package com.perrystreet.woof.usecase.account

import com.perrystreet.woof.models.dog.DogProfile
import com.perrystreet.woof.repositories.account.AccountRepository
import io.reactivex.rxjava3.core.Single
import org.koin.core.annotation.Factory

@Factory
class GetAccountUseCase(
    private val accountRepository: AccountRepository,
) {
    operator fun invoke(): Single<DogProfile> = accountRepository.getAccount()
}
