package com.perrystreet.woof.presentation.profile.viewmodel

import com.perrystreet.woof.models.dog.Dog
import com.perrystreet.woof.presentation.common.rx.UiObservable
import com.perrystreet.woof.presentation.common.rx.UiObservable.Companion.asUiObservable
import com.perrystreet.woof.presentation.common.viewmodel.StateDerivingViewModel
import com.perrystreet.woof.presentation.profile.uimodel.ProfileToastUIModel
import com.perrystreet.woof.usecase.woofs.HasWoofedDogUseCase
import com.perrystreet.woof.usecase.woofs.SendWoofUseCase
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.subjects.BehaviorSubject
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.InjectedParam
import java.util.Optional

@KoinViewModel
class ProfileWoofViewModel(
    @InjectedParam private val dog: Dog,
    private val sendWoofUseCase: SendWoofUseCase,
    hasWoofedDogUseCase: HasWoofedDogUseCase,
) : StateDerivingViewModel<ProfileWoofViewModel.State>() {

    data class State(
        val hasWoofed: Boolean,
        val toast: ProfileToastUIModel?,
    )

    private val toast: BehaviorSubject<Optional<ProfileToastUIModel>> = BehaviorSubject.createDefault(Optional.empty())

    override val state: UiObservable<State> = Observable.combineLatest(
        hasWoofedDogUseCase(dog),
        toast,
    ) { hasWoofed, toast ->
        State(hasWoofed = hasWoofed, toast = toast.orElse(null))
    }.asUiObservable(State(hasWoofed = false, toast = null))

    fun onWoofTap() {
        disposables += sendWoofUseCase(dog).subscribe(
            { toast.onNext(Optional.of(ProfileToastUIModel.WoofSent(dog.name))) },
            { error -> mutableError.onNext(Optional.of(error)) },
        )
    }

    fun onToastDismiss() {
        toast.onNext(Optional.empty())
    }
}
