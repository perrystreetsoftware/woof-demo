package com.perrystreet.woof.designsystem.atomic.organisms.overflowmenu

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable

@Immutable
data class OrgOverflowMenuItem(
    val text: String,
    @DrawableRes val iconRes: Int,
    val onTap: () -> Unit,
    val isDestructive: Boolean = false,
)
