package com.perrystreet.woof.presentation.browse.ui

import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.perrystreet.woof.designsystem.atomic.atoms.placeholder.AtomPlaceholder
import com.perrystreet.woof.designsystem.atomic.atoms.placeholder.roles.ShapePlaceholderRole
import com.perrystreet.woof.designsystem.atomic.organisms.emptystate.OrgErrorState
import com.perrystreet.woof.designsystem.atomic.organisms.header.OrgNavigationHeaderBranded
import com.perrystreet.woof.designsystem.atomic.templates.TemplateCenteredContent
import com.perrystreet.woof.designsystem.atomic.templates.TemplateGrid
import com.perrystreet.woof.designsystem.preview.PreviewDevices
import com.perrystreet.woof.designsystem.preview.ThemeProvider
import com.perrystreet.woof.designsystem.preview.ThemedScreenPreview
import com.perrystreet.woof.designsystem.theme.ITheme
import com.perrystreet.woof.presentation.browse.ui.components.DogCell
import com.perrystreet.woof.presentation.browse.ui.preview.BrowsePreviewData
import com.perrystreet.woof.presentation.browse.uimodel.DogCellUIModel
import com.perrystreet.woof.presentation.browse.viewmodel.BrowseViewModel
import com.perrystreet.woof.resources.R

@Composable
fun BrowseScreen(
    state: BrowseViewModel.State,
    onCellAppear: (DogCellUIModel) -> Unit,
    onCellTap: (DogCellUIModel) -> Unit,
    onRetryTap: () -> Unit,
) {
    when (state) {
        BrowseViewModel.State.Loading -> BrowseLoadingScreen()
        is BrowseViewModel.State.Loaded -> BrowseLoadedScreen(
            state = state,
            onCellAppear = onCellAppear,
            onCellTap = onCellTap,
        )
        BrowseViewModel.State.Error -> BrowseErrorScreen(onRetryTap = onRetryTap)
    }
}

@Composable
private fun BrowseLoadingScreen() {
    TemplateGrid(topBar = { OrgNavigationHeaderBranded() }) {
        items(PlaceholderCellCount) {
            AtomPlaceholder(role = ShapePlaceholderRole.Card)
        }
    }
}

@Composable
private fun BrowseLoadedScreen(
    state: BrowseViewModel.State.Loaded,
    onCellAppear: (DogCellUIModel) -> Unit,
    onCellTap: (DogCellUIModel) -> Unit,
) {
    TemplateGrid(topBar = { OrgNavigationHeaderBranded() }) {
        items(items = state.cells, key = { cell -> cell.id }) { cell ->
            DogCell(cell = cell, onCellAppear = onCellAppear, onCellTap = onCellTap)
        }
        items(state.loadingMoreCellCount) {
            AtomPlaceholder(role = ShapePlaceholderRole.Card)
        }
    }
}

@Composable
private fun BrowseErrorScreen(onRetryTap: () -> Unit) {
    TemplateCenteredContent(topBar = { OrgNavigationHeaderBranded() }) {
        OrgErrorState(
            title = stringResource(R.string.browse_error_title),
            message = stringResource(R.string.browse_error_message),
            actionText = stringResource(R.string.browse_error_retry),
            onActionTap = onRetryTap,
        )
    }
}

private const val PlaceholderCellCount = 30

@PreviewDevices
@Composable
private fun BrowseScreenLoadedPreview(@PreviewParameter(ThemeProvider::class) theme: ITheme) {
    ThemedScreenPreview(theme = theme) {
        BrowseScreen(
            state = BrowsePreviewData.loaded(),
            onCellAppear = {},
            onCellTap = {},
            onRetryTap = {},
        )
    }
}

@PreviewDevices
@Composable
private fun BrowseScreenLoadingPreview(@PreviewParameter(ThemeProvider::class) theme: ITheme) {
    ThemedScreenPreview(theme = theme) {
        BrowseScreen(
            state = BrowseViewModel.State.Loading,
            onCellAppear = {},
            onCellTap = {},
            onRetryTap = {},
        )
    }
}

@PreviewDevices
@Composable
private fun BrowseScreenErrorPreview(@PreviewParameter(ThemeProvider::class) theme: ITheme) {
    ThemedScreenPreview(theme = theme) {
        BrowseScreen(
            state = BrowseViewModel.State.Error,
            onCellAppear = {},
            onCellTap = {},
            onRetryTap = {},
        )
    }
}
