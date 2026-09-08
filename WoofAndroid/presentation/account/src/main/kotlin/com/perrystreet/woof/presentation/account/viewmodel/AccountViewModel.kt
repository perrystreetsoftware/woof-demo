package com.perrystreet.woof.presentation.account.viewmodel

import com.perrystreet.woof.presentation.account.mapper.DogProfileDomainToAccountUIModelMapper
import com.perrystreet.woof.presentation.account.uimodel.AccountUIModel
import com.perrystreet.woof.presentation.common.viewmodel.StateProducingViewModel
import com.perrystreet.woof.usecase.account.GetAccountUseCase
import io.reactivex.rxjava3.kotlin.plusAssign
import org.koin.android.annotation.KoinViewModel
import java.util.Optional

@KoinViewModel
class AccountViewModel(
    private val getAccountUseCase: GetAccountUseCase,
    private val accountMapper: DogProfileDomainToAccountUIModelMapper,
) : StateProducingViewModel<AccountViewModel.State>(initialValue = State.Loading) {

    sealed class State {
        data object Loading : State()

        data class Loaded(val account: AccountUIModel) : State()
    }

    override fun onFirstAppear() {
        disposables += getAccountUseCase().subscribe(
            { profile -> _state.onNext(State.Loaded(accountMapper(profile))) },
            { error -> mutableError.onNext(Optional.of(error)) },
        )
    }
}
