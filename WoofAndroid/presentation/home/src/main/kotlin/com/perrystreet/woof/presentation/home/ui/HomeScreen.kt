package com.perrystreet.woof.presentation.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.perrystreet.woof.designsystem.atomic.organisms.emptystate.OrgEmptyState
import com.perrystreet.woof.designsystem.atomic.organisms.navbar.OrgBottomNavigationBar
import com.perrystreet.woof.designsystem.atomic.organisms.navbar.OrgBottomNavigationItem
import com.perrystreet.woof.designsystem.atomic.templates.TemplateBottomNavigation
import com.perrystreet.woof.designsystem.preview.PreviewDevices
import com.perrystreet.woof.designsystem.preview.ThemeProvider
import com.perrystreet.woof.designsystem.preview.ThemedScreenPreview
import com.perrystreet.woof.designsystem.theme.ITheme
import com.perrystreet.woof.presentation.home.ui.extensions.HomeTabUIModelExtensions.toBottomNavigationRole
import com.perrystreet.woof.presentation.home.uimodel.HomeTabUIModel
import com.perrystreet.woof.presentation.home.viewmodel.HomeViewModel
import com.perrystreet.woof.resources.R

@Composable
fun HomeScreen(
    state: HomeViewModel.State,
    onTabSelect: (HomeTabUIModel) -> Unit,
    tabContent: @Composable (tab: HomeTabUIModel) -> Unit,
) {
    TemplateBottomNavigation(
        bottomBar = {
            OrgBottomNavigationBar(
                items = state.tabs.map { tab ->
                    OrgBottomNavigationItem(
                        role = tab.toBottomNavigationRole(),
                        isSelected = tab == state.selectedTab,
                        onTap = { onTabSelect(tab) },
                    )
                },
            )
        },
    ) {
        tabContent(state.selectedTab)
    }
}

@PreviewDevices
@Composable
private fun HomeScreenPreview(@PreviewParameter(ThemeProvider::class) theme: ITheme) {
    ThemedScreenPreview(theme = theme) {
        HomeScreen(state = HomeViewModel.State.Initial, onTabSelect = {}) { tab ->
            OrgEmptyState(title = stringResource(R.string.app_name), message = tab.name)
        }
    }
}
