package com.perrystreet.woof.presentation.profile.ui.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.perrystreet.woof.designsystem.atomic.organisms.overflowmenu.OrgOverflowMenuItem
import com.perrystreet.woof.presentation.profile.uimodel.ProfileOverflowMenuItemUIModel
import com.perrystreet.woof.resources.R

object ProfileOverflowMenuItemUIModelExtensions {
    @Composable
    fun ProfileOverflowMenuItemUIModel.toOverflowMenuItem(onTap: () -> Unit): OrgOverflowMenuItem = when (this) {
        ProfileOverflowMenuItemUIModel.Report -> OrgOverflowMenuItem(
            text = stringResource(R.string.profile_menu_report),
            iconRes = R.drawable.ic_flag,
            onTap = onTap,
            isDestructive = true,
        )
        ProfileOverflowMenuItemUIModel.Block -> OrgOverflowMenuItem(
            text = stringResource(R.string.profile_menu_block),
            iconRes = R.drawable.ic_block,
            onTap = onTap,
            isDestructive = true,
        )
    }
}
