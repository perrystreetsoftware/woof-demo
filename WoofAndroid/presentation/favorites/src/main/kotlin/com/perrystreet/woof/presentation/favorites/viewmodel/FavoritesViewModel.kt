package com.perrystreet.woof.presentation.favorites.viewmodel

import com.perrystreet.woof.presentation.common.rx.UiObservable
import com.perrystreet.woof.presentation.common.rx.UiObservable.Companion.asUiObservable
import com.perrystreet.woof.presentation.common.viewmodel.StateDerivingViewModel
import com.perrystreet.woof.presentation.favorites.mapper.DogDomainToFavoriteCellUIModelMapper
import com.perrystreet.woof.presentation.favorites.uimodel.FavoriteCellUIModel
import com.perrystreet.woof.usecase.favorites.GetFavoriteDogsUseCase
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class FavoritesViewModel(
    private val cellMapper: DogDomainToFavoriteCellUIModelMapper,
    getFavoriteDogsUseCase: GetFavoriteDogsUseCase,
) : StateDerivingViewModel<FavoritesViewModel.State>() {

    sealed class State {
        data object Empty : State()

        data class Loaded(val cells: List<FavoriteCellUIModel>) : State()
    }

    override val state: UiObservable<State> = getFavoriteDogsUseCase()
        .map { dogs ->
            when (dogs.isEmpty()) {
                true -> State.Empty
                false -> State.Loaded(cells = dogs.map { cellMapper(it) })
            }
        }
        .asUiObservable(State.Empty)
}
