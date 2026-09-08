package com.perrystreet.woof.presentation.profile.viewmodel

import com.perrystreet.woof.presentation.common.rx.UiObservable
import com.perrystreet.woof.presentation.common.rx.UiObservable.Companion.asUiObservable
import com.perrystreet.woof.presentation.common.viewmodel.StateDerivingViewModel
import com.perrystreet.woof.presentation.profile.mapper.DogDomainToProfilePageUIModelMapper
import com.perrystreet.woof.presentation.profile.uimodel.ProfilePageUIModel
import com.perrystreet.woof.usecase.dogs.GetDogsFeedUseCase
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.InjectedParam

@KoinViewModel
class ProfilePagerViewModel(
    @InjectedParam private val dogId: Long,
    private val pageMapper: DogDomainToProfilePageUIModelMapper,
    getDogsFeedUseCase: GetDogsFeedUseCase,
) : StateDerivingViewModel<ProfilePagerViewModel.State>() {

    data class State(
        val pages: List<ProfilePageUIModel>,
        val initialPage: Int,
    ) {
        companion object {
            val Empty = State(pages = emptyList(), initialPage = 0)
        }
    }

    override val state: UiObservable<State> = getDogsFeedUseCase()
        .map { feed ->
            val pages = feed.dogs.map { pageMapper(it) }
            State(
                pages = pages,
                initialPage = pages.indexOfFirst { it.id == dogId }.coerceAtLeast(0),
            )
        }
        .distinctUntilChanged()
        .asUiObservable(State.Empty)
}
