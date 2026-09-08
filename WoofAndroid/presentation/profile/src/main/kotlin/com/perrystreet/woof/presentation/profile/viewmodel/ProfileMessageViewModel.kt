package com.perrystreet.woof.presentation.profile.viewmodel

import com.perrystreet.woof.models.dog.Dog
import com.perrystreet.woof.presentation.common.viewmodel.StateProducingViewModel
import com.perrystreet.woof.presentation.profile.uimodel.ProfileToastUIModel
import com.perrystreet.woof.usecase.messages.SendMessageUseCase
import io.reactivex.rxjava3.kotlin.plusAssign
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.InjectedParam
import java.util.Optional

@KoinViewModel
class ProfileMessageViewModel(
    @InjectedParam private val dog: Dog,
    private val sendMessageUseCase: SendMessageUseCase,
) : StateProducingViewModel<ProfileMessageViewModel.State>(initialValue = State.Initial) {

    data class State(
        val text: String,
        val toast: ProfileToastUIModel?,
    ) {
        val isSendEnabled: Boolean
            get() = text.isNotBlank()

        companion object {
            val Initial = State(text = "", toast = null)
        }
    }

    fun onTextChange(text: String) {
        _state.onNext(currentState.copy(text = text))
    }

    fun onSendTap() {
        val text = currentState.text
        disposables += sendMessageUseCase(dog, text).subscribe(
            { _state.onNext(currentState.copy(text = "", toast = ProfileToastUIModel.MessageSent(dog.name))) },
            { error -> mutableError.onNext(Optional.of(error)) },
        )
    }

    fun onToastDismiss() {
        _state.onNext(currentState.copy(toast = null))
    }
}
