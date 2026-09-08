package com.perrystreet.woof.designsystem.atomic.organisms.header

import androidx.compose.runtime.Immutable
import com.perrystreet.woof.designsystem.atomic.molecules.button.roles.IconButtonRole

@Immutable
data class OrgNavigationHeaderActionItem(
    val role: IconButtonRole,
    val onTap: () -> Unit,
    val isActive: Boolean = false,
)
