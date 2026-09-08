package com.perrystreet.woof.presentation.grid.viewmodel

import com.perrystreet.woof.models.dog.DogsFeed
import com.perrystreet.woof.presentation.common.viewmodel.StateProducingViewModel
import com.perrystreet.woof.presentation.grid.mapper.DogDomainToCellUIModelMapper
import com.perrystreet.woof.presentation.grid.uimodel.DogCellUIModel
import com.perrystreet.woof.usecase.dogs.GetDogsFeedUseCase
import com.perrystreet.woof.usecase.dogs.LoadNextDogsPageUseCase
import com.perrystreet.woof.utils.guard
import io.reactivex.rxjava3.kotlin.plusAssign
import org.koin.android.annotation.KoinViewModel
import java.util.Optional

@KoinViewModel
class GridViewModel(
    private val getDogsFeedUseCase: GetDogsFeedUseCase,
    private val loadNextDogsPageUseCase: LoadNextDogsPageUseCase,
    private val cellMapper: DogDomainToCellUIModelMapper,
) : StateProducingViewModel<GridViewModel.State>(initialValue = State.Loading) {

    sealed class State {
        data object Loading : State()

        data class Loaded(
            val cells: List<DogCellUIModel>,
            val isLoadingMore: Boolean,
            val hasMore: Boolean,
        ) : State() {
            val loadingMoreCellCount: Int
                get() = when (isLoadingMore) {
                    true -> LoadingMoreCellCount
                    false -> 0
                }
        }

        data object Error : State()
    }

    private var isLoadingPage = false

    override fun onFirstAppear() {
        disposables += getDogsFeedUseCase().subscribe { feed -> onFeedUpdate(feed) }
        loadNextPage()
    }

    fun onCellAppear(cell: DogCellUIModel) {
        val loaded = currentState as? State.Loaded
        guard(loaded != null && loaded.hasMore && !isLoadingPage) { return }
        guard(cell.index >= loaded.cells.size - LoadMoreThreshold) { return }
        loadNextPage()
    }

    fun onRetryTap() {
        _state.onNext(State.Loading)
        loadNextPage()
    }

    private fun onFeedUpdate(feed: DogsFeed) {
        guard(feed.dogs.isNotEmpty()) { return }
        _state.onNext(
            State.Loaded(
                cells = feed.dogs.mapIndexed { index, dog -> cellMapper(dog, index) },
                isLoadingMore = isLoadingPage,
                hasMore = feed.hasMore,
            ),
        )
    }

    private fun loadNextPage() {
        isLoadingPage = true
        updateLoadingMore()
        disposables += loadNextDogsPageUseCase().subscribe(
            {
                isLoadingPage = false
                updateLoadingMore()
            },
            { error ->
                isLoadingPage = false
                onPageError(error)
            },
        )
    }

    private fun updateLoadingMore() {
        val loaded = currentState as? State.Loaded
        guard(loaded != null) { return }
        _state.onNext(loaded.copy(isLoadingMore = isLoadingPage))
    }

    private fun onPageError(error: Throwable) {
        when (currentState) {
            is State.Loaded -> {
                updateLoadingMore()
                mutableError.onNext(Optional.of(error))
            }
            State.Loading, State.Error -> _state.onNext(State.Error)
        }
    }

    private companion object {
        const val LoadMoreThreshold = 12
        const val LoadingMoreCellCount = 6
    }
}
