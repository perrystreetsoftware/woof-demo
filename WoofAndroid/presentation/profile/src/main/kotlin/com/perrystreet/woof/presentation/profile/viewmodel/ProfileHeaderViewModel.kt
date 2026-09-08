package com.perrystreet.woof.presentation.profile.viewmodel

import com.perrystreet.woof.models.dog.Dog
import com.perrystreet.woof.presentation.common.rx.UiObservable
import com.perrystreet.woof.presentation.common.rx.UiObservable.Companion.asUiObservable
import com.perrystreet.woof.presentation.common.viewmodel.StateDerivingViewModel
import com.perrystreet.woof.presentation.profile.uimodel.ProfileOverflowMenuItemUIModel
import com.perrystreet.woof.usecase.favorites.IsDogFavoriteUseCase
import com.perrystreet.woof.usecase.favorites.ToggleDogFavoriteUseCase
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.subjects.BehaviorSubject
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.InjectedParam
import java.util.Optional

@KoinViewModel
class ProfileHeaderViewModel(
    @InjectedParam private val dog: Dog,
    private val toggleDogFavoriteUseCase: ToggleDogFavoriteUseCase,
    isDogFavoriteUseCase: IsDogFavoriteUseCase,
) : StateDerivingViewModel<ProfileHeaderViewModel.State>() {

    data class State(
        val name: String,
        val isFavorite: Boolean,
        val isOverflowExpanded: Boolean,
        val overflowItems: List<ProfileOverflowMenuItemUIModel>,
    )

    private val overflowExpanded: BehaviorSubject<Boolean> = BehaviorSubject.createDefault(false)

    override val state: UiObservable<State> = Observable.combineLatest(
        isDogFavoriteUseCase(dog),
        overflowExpanded,
    ) { isFavorite, isOverflowExpanded ->
        State(
            name = dog.name,
            isFavorite = isFavorite,
            isOverflowExpanded = isOverflowExpanded,
            overflowItems = ProfileOverflowMenuItemUIModel.entries,
        )
    }.asUiObservable(
        State(
            name = dog.name,
            isFavorite = false,
            isOverflowExpanded = false,
            overflowItems = ProfileOverflowMenuItemUIModel.entries,
        ),
    )

    fun onFavoriteTap() {
        disposables += toggleDogFavoriteUseCase(dog).subscribe(
            {},
            { error -> mutableError.onNext(Optional.of(error)) },
        )
    }

    fun onOverflowExpandedChange(isExpanded: Boolean) {
        overflowExpanded.onNext(isExpanded)
    }
}
