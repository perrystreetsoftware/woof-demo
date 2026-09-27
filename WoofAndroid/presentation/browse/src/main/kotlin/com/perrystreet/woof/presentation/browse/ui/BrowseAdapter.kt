package com.perrystreet.woof.presentation.browse.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import com.perrystreet.woof.presentation.common.error.ErrorAdapter
import com.perrystreet.woof.presentation.browse.ui.extensions.BrowseErrorToToastMapper
import com.perrystreet.woof.presentation.browse.viewmodel.BrowseViewModel
import com.perrystreet.woof.presentation.navigation.LocalNavigator
import com.perrystreet.woof.presentation.navigation.WoofDestination
import org.koin.androidx.compose.koinViewModel

@Composable
fun BrowseAdapter(viewModel: BrowseViewModel = koinViewModel()) {
    val navigator = LocalNavigator.current
    val state by viewModel.state.subscribeAsState()

    LaunchedEffect(Unit) {
        viewModel.onViewAppear()
    }

    BrowseScreen(
        state = state,
        onCellAppear = viewModel::onCellAppear,
        onCellTap = { cell -> navigator.goTo(WoofDestination.Profile(dogId = cell.id)) },
        onRetryTap = viewModel::onRetryTap,
    )

    ErrorAdapter(
        viewModels = listOf(viewModel),
        errorMapper = BrowseErrorToToastMapper(),
    )
}
