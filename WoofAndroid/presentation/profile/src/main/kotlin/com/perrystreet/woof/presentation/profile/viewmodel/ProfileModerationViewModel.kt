package com.perrystreet.woof.presentation.profile.viewmodel

import com.perrystreet.woof.models.dog.Dog
import com.perrystreet.woof.presentation.common.viewmodel.StateProducingViewModel
import com.perrystreet.woof.presentation.navigation.INavigator
import com.perrystreet.woof.presentation.profile.uimodel.ProfileModerationDialogUIModel
import com.perrystreet.woof.presentation.profile.uimodel.ProfileToastUIModel
import com.perrystreet.woof.usecase.moderation.BlockDogUseCase
import com.perrystreet.woof.usecase.moderation.ReportDogUseCase
import com.perrystreet.woof.utils.guard
import io.reactivex.rxjava3.kotlin.plusAssign
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.InjectedParam
import java.util.Optional

@KoinViewModel
class ProfileModerationViewModel(
    @InjectedParam private val dog: Dog,
    private val reportDogUseCase: ReportDogUseCase,
    private val blockDogUseCase: BlockDogUseCase,
    private val navigator: INavigator,
) : StateProducingViewModel<ProfileModerationViewModel.State>(initialValue = State.Initial) {

    data class State(
        val dialog: ProfileModerationDialogUIModel?,
        val toast: ProfileToastUIModel?,
    ) {
        companion object {
            val Initial = State(dialog = null, toast = null)
        }
    }

    fun onReportTap() {
        _state.onNext(currentState.copy(dialog = ProfileModerationDialogUIModel.Report(dog.name)))
    }

    fun onBlockTap() {
        _state.onNext(currentState.copy(dialog = ProfileModerationDialogUIModel.Block(dog.name)))
    }

    fun onDialogDismiss() {
        _state.onNext(currentState.copy(dialog = null))
    }

    fun onDialogConfirm() {
        val dialog = currentState.dialog
        guard(dialog != null) { return }
        _state.onNext(currentState.copy(dialog = null))
        when (dialog) {
            is ProfileModerationDialogUIModel.Report -> report()
            is ProfileModerationDialogUIModel.Block -> block()
        }
    }

    fun onToastDismiss() {
        _state.onNext(currentState.copy(toast = null))
    }

    private fun report() {
        disposables += reportDogUseCase(dog).subscribe(
            { _state.onNext(currentState.copy(toast = ProfileToastUIModel.ReportSent)) },
            { error -> mutableError.onNext(Optional.of(error)) },
        )
    }

    private fun block() {
        disposables += blockDogUseCase(dog).subscribe(
            { navigator.back() },
            { error -> mutableError.onNext(Optional.of(error)) },
        )
    }
}
