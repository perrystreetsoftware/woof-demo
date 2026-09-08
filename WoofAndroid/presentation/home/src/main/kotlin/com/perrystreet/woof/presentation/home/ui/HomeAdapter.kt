package com.perrystreet.woof.presentation.home.ui

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import com.perrystreet.woof.presentation.account.ui.AccountAdapter
import com.perrystreet.woof.presentation.favorites.ui.FavoritesAdapter
import com.perrystreet.woof.presentation.grid.ui.GridAdapter
import com.perrystreet.woof.presentation.home.uimodel.HomeTabUIModel
import com.perrystreet.woof.presentation.home.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeAdapter(viewModel: HomeViewModel = koinViewModel()) {
    val state by viewModel.state.subscribeAsState()
    val saveableStateHolder = rememberSaveableStateHolder()

    BackHandler(enabled = state.isBackHandled, onBack = viewModel::onBackTap)

    HomeScreen(
        state = state,
        onTabSelect = viewModel::onTabSelect,
    ) { tab ->
        saveableStateHolder.SaveableStateProvider(key = tab) {
            when (tab) {
                HomeTabUIModel.Browse -> GridAdapter()
                HomeTabUIModel.Favorites -> FavoritesAdapter()
                HomeTabUIModel.Account -> AccountAdapter()
            }
        }
    }
}
