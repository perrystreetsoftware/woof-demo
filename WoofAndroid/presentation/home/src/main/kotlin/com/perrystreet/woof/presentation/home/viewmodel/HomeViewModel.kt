package com.perrystreet.woof.presentation.home.viewmodel

import com.perrystreet.woof.presentation.common.viewmodel.StateProducingViewModel
import com.perrystreet.woof.presentation.home.uimodel.HomeTabUIModel
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HomeViewModel : StateProducingViewModel<HomeViewModel.State>(initialValue = State.Initial) {

    data class State(
        val selectedTab: HomeTabUIModel,
        val tabs: List<HomeTabUIModel>,
    ) {
        val isBackHandled: Boolean
            get() = selectedTab != HomeTabUIModel.Browse

        companion object {
            val Initial = State(selectedTab = HomeTabUIModel.Browse, tabs = HomeTabUIModel.entries)
        }
    }

    fun onTabSelect(tab: HomeTabUIModel) {
        _state.onNext(currentState.copy(selectedTab = tab))
    }

    fun onBackTap() {
        _state.onNext(currentState.copy(selectedTab = HomeTabUIModel.Browse))
    }
}
