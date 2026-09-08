package com.perrystreet.woof.presentation.profile.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.perrystreet.woof.designsystem.atomic.organisms.emptystate.OrgErrorState
import com.perrystreet.woof.designsystem.atomic.templates.TemplatePager
import com.perrystreet.woof.designsystem.preview.PreviewDevices
import com.perrystreet.woof.designsystem.preview.ThemeProvider
import com.perrystreet.woof.designsystem.preview.ThemedScreenPreview
import com.perrystreet.woof.designsystem.theme.ITheme
import com.perrystreet.woof.presentation.profile.ui.preview.ProfilePreviewData
import com.perrystreet.woof.presentation.profile.uimodel.ProfilePageUIModel
import com.perrystreet.woof.presentation.profile.viewmodel.ProfilePagerViewModel
import com.perrystreet.woof.utils.guard

@Composable
fun ProfilePagerScreen(
    state: ProfilePagerViewModel.State,
    pageContent: @Composable (page: ProfilePageUIModel) -> Unit,
) {
    guard(state.pages.isNotEmpty()) { return }

    TemplatePager(
        pageCount = state.pages.size,
        initialPage = state.initialPage,
        key = { page -> state.pages[page].id },
    ) { page ->
        pageContent(state.pages[page])
    }
}

@PreviewDevices
@Composable
private fun ProfilePagerScreenPreview(@PreviewParameter(ThemeProvider::class) theme: ITheme) {
    ThemedScreenPreview(theme = theme) {
        ProfilePagerScreen(
            state = ProfilePagerViewModel.State(pages = listOf(ProfilePreviewData.page()), initialPage = 0),
        ) { page ->
            OrgErrorState(title = page.name, message = page.photoUrl, actionText = "", onActionTap = {})
        }
    }
}
