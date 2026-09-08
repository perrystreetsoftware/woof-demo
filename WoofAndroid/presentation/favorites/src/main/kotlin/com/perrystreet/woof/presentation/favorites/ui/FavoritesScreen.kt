package com.perrystreet.woof.presentation.favorites.ui

import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.perrystreet.woof.designsystem.atomic.organisms.card.OrgPhotoCard
import com.perrystreet.woof.designsystem.atomic.organisms.emptystate.OrgEmptyState
import com.perrystreet.woof.designsystem.atomic.organisms.header.OrgNavigationHeaderTitle
import com.perrystreet.woof.designsystem.atomic.templates.TemplateCenteredContent
import com.perrystreet.woof.designsystem.atomic.templates.TemplateGrid
import com.perrystreet.woof.designsystem.preview.PreviewDevices
import com.perrystreet.woof.designsystem.preview.ThemeProvider
import com.perrystreet.woof.designsystem.preview.ThemedScreenPreview
import com.perrystreet.woof.designsystem.theme.ITheme
import com.perrystreet.woof.presentation.common.image.AsyncImageStateExtensions.rememberAsyncImageState
import com.perrystreet.woof.presentation.favorites.ui.preview.FavoritesPreviewData
import com.perrystreet.woof.presentation.favorites.uimodel.FavoriteCellUIModel
import com.perrystreet.woof.presentation.favorites.viewmodel.FavoritesViewModel
import com.perrystreet.woof.resources.R

@Composable
fun FavoritesScreen(
    state: FavoritesViewModel.State,
    onCellTap: (FavoriteCellUIModel) -> Unit,
) {
    when (state) {
        FavoritesViewModel.State.Empty -> FavoritesEmptyScreen()
        is FavoritesViewModel.State.Loaded -> FavoritesLoadedScreen(state = state, onCellTap = onCellTap)
    }
}

@Composable
private fun FavoritesEmptyScreen() {
    TemplateCenteredContent(topBar = { OrgNavigationHeaderTitle(title = stringResource(R.string.favorites_title)) }) {
        OrgEmptyState(
            title = stringResource(R.string.favorites_empty_title),
            message = stringResource(R.string.favorites_empty_message),
        )
    }
}

@Composable
private fun FavoritesLoadedScreen(
    state: FavoritesViewModel.State.Loaded,
    onCellTap: (FavoriteCellUIModel) -> Unit,
) {
    TemplateGrid(topBar = { OrgNavigationHeaderTitle(title = stringResource(R.string.favorites_title)) }) {
        items(items = state.cells, key = { cell -> cell.id }) { cell ->
            OrgPhotoCard(
                title = cell.name,
                imageState = rememberAsyncImageState(url = cell.photoUrl),
                contentDescription = stringResource(R.string.accessibility_dog_photo, cell.name),
                onTap = { onCellTap(cell) },
            )
        }
    }
}

@PreviewDevices
@Composable
private fun FavoritesScreenLoadedPreview(@PreviewParameter(ThemeProvider::class) theme: ITheme) {
    ThemedScreenPreview(theme = theme) {
        FavoritesScreen(state = FavoritesPreviewData.loaded(), onCellTap = {})
    }
}

@PreviewDevices
@Composable
private fun FavoritesScreenEmptyPreview(@PreviewParameter(ThemeProvider::class) theme: ITheme) {
    ThemedScreenPreview(theme = theme) {
        FavoritesScreen(state = FavoritesViewModel.State.Empty, onCellTap = {})
    }
}
