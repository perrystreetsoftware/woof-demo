package com.perrystreet.woof.presentation.profile.ui.extensions

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.perrystreet.woof.designsystem.atomic.atoms.icon.roles.IconColorRole
import com.perrystreet.woof.designsystem.atomic.organisms.overflowmenu.state.OverflowMenuState
import com.perrystreet.woof.presentation.profile.viewmodel.ProfileHeaderViewModel
import com.perrystreet.woof.resources.R

object ProfileHeaderStateExtensions {
    @DrawableRes
    fun ProfileHeaderViewModel.State.favoriteIconRes(): Int = when (isFavorite) {
        true -> R.drawable.ic_star_filled
        false -> R.drawable.ic_star_outline
    }

    @Composable
    fun ProfileHeaderViewModel.State.favoriteContentDescription(): String = when (isFavorite) {
        true -> stringResource(R.string.accessibility_remove_favorite)
        false -> stringResource(R.string.accessibility_add_favorite)
    }

    fun ProfileHeaderViewModel.State.favoriteColorRole(): IconColorRole = when (isFavorite) {
        true -> IconColorRole.Recent
        false -> IconColorRole.OnScrim
    }

    fun ProfileHeaderViewModel.State.overflowMenuState(): OverflowMenuState = when (isOverflowExpanded) {
        true -> OverflowMenuState.Expanded
        false -> OverflowMenuState.Default
    }
}
