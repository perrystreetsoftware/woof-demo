package com.perrystreet.woof.designsystem.atomic.organisms.header

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import com.perrystreet.woof.designsystem.atomic.atoms.icon.roles.IconColorRole

@Immutable
data class OrgNavigationHeaderActionItem(
    @DrawableRes val iconRes: Int,
    val contentDescription: String,
    val colorRole: IconColorRole,
    val onTap: () -> Unit,
)
