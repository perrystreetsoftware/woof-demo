package com.perrystreet.woof.presentation.favorites.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import com.perrystreet.woof.presentation.favorites.viewmodel.FavoritesViewModel
import com.perrystreet.woof.presentation.navigation.LocalNavigator
import com.perrystreet.woof.presentation.navigation.WoofDestination
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoritesAdapter(viewModel: FavoritesViewModel = koinViewModel()) {
    val navigator = LocalNavigator.current
    val state by viewModel.state.subscribeAsState()

    LaunchedEffect(Unit) {
        viewModel.onViewAppear()
    }

    FavoritesScreen(
        state = state,
        onCellTap = { cell -> navigator.goTo(WoofDestination.Profile(dogId = cell.id)) },
    )
}
