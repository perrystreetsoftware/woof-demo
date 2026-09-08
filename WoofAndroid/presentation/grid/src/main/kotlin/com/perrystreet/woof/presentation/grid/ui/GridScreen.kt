package com.perrystreet.woof.presentation.grid.ui

import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.perrystreet.woof.designsystem.atomic.molecules.placeholder.MolCardPlaceholder
import com.perrystreet.woof.designsystem.atomic.organisms.emptystate.OrgErrorState
import com.perrystreet.woof.designsystem.atomic.organisms.header.OrgNavigationHeaderBranded
import com.perrystreet.woof.designsystem.atomic.templates.TemplateCenteredContent
import com.perrystreet.woof.designsystem.atomic.templates.TemplateGrid
import com.perrystreet.woof.designsystem.preview.PreviewDevices
import com.perrystreet.woof.designsystem.preview.ThemeProvider
import com.perrystreet.woof.designsystem.preview.ThemedScreenPreview
import com.perrystreet.woof.designsystem.theme.ITheme
import com.perrystreet.woof.presentation.grid.ui.components.DogCell
import com.perrystreet.woof.presentation.grid.ui.preview.GridPreviewData
import com.perrystreet.woof.presentation.grid.uimodel.DogCellUIModel
import com.perrystreet.woof.presentation.grid.viewmodel.GridViewModel
import com.perrystreet.woof.resources.R

@Composable
fun GridScreen(
    state: GridViewModel.State,
    onCellAppear: (DogCellUIModel) -> Unit,
    onCellTap: (DogCellUIModel) -> Unit,
    onRetryTap: () -> Unit,
) {
    when (state) {
        GridViewModel.State.Loading -> GridLoadingScreen()
        is GridViewModel.State.Loaded -> GridLoadedScreen(
            state = state,
            onCellAppear = onCellAppear,
            onCellTap = onCellTap,
        )
        GridViewModel.State.Error -> GridErrorScreen(onRetryTap = onRetryTap)
    }
}

@Composable
private fun GridLoadingScreen() {
    TemplateGrid(topBar = { OrgNavigationHeaderBranded() }) {
        items(PlaceholderCellCount) {
            MolCardPlaceholder()
        }
    }
}

@Composable
private fun GridLoadedScreen(
    state: GridViewModel.State.Loaded,
    onCellAppear: (DogCellUIModel) -> Unit,
    onCellTap: (DogCellUIModel) -> Unit,
) {
    TemplateGrid(topBar = { OrgNavigationHeaderBranded() }) {
        items(items = state.cells, key = { cell -> cell.id }) { cell ->
            DogCell(cell = cell, onCellAppear = onCellAppear, onCellTap = onCellTap)
        }
        items(state.loadingMoreCellCount) {
            MolCardPlaceholder()
        }
    }
}

@Composable
private fun GridErrorScreen(onRetryTap: () -> Unit) {
    TemplateCenteredContent(topBar = { OrgNavigationHeaderBranded() }) {
        OrgErrorState(
            title = stringResource(R.string.grid_error_title),
            message = stringResource(R.string.grid_error_message),
            actionText = stringResource(R.string.grid_error_retry),
            onActionTap = onRetryTap,
        )
    }
}

private const val PlaceholderCellCount = 30

@PreviewDevices
@Composable
private fun GridScreenLoadedPreview(@PreviewParameter(ThemeProvider::class) theme: ITheme) {
    ThemedScreenPreview(theme = theme) {
        GridScreen(
            state = GridPreviewData.loaded(),
            onCellAppear = {},
            onCellTap = {},
            onRetryTap = {},
        )
    }
}

@PreviewDevices
@Composable
private fun GridScreenLoadingPreview(@PreviewParameter(ThemeProvider::class) theme: ITheme) {
    ThemedScreenPreview(theme = theme) {
        GridScreen(
            state = GridViewModel.State.Loading,
            onCellAppear = {},
            onCellTap = {},
            onRetryTap = {},
        )
    }
}

@PreviewDevices
@Composable
private fun GridScreenErrorPreview(@PreviewParameter(ThemeProvider::class) theme: ITheme) {
    ThemedScreenPreview(theme = theme) {
        GridScreen(
            state = GridViewModel.State.Error,
            onCellAppear = {},
            onCellTap = {},
            onRetryTap = {},
        )
    }
}
