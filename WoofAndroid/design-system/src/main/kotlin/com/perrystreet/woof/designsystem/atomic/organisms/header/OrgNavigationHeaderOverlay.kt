package com.perrystreet.woof.designsystem.atomic.organisms.header

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.perrystreet.woof.designsystem.atomic._tokens.sizing.SizingRoles
import com.perrystreet.woof.designsystem.atomic._tokens.spacing.PaddingRoles
import com.perrystreet.woof.designsystem.atomic.molecules.button.MolIconButton
import com.perrystreet.woof.designsystem.atomic.molecules.button.roles.IconButtonRole
import com.perrystreet.woof.designsystem.atomic.organisms.overflowmenu.OrgOverflowMenuButton
import com.perrystreet.woof.designsystem.atomic.organisms.overflowmenu.OrgOverflowMenuItem

@Composable
fun OrgNavigationHeaderOverlay(
    onBackTap: () -> Unit,
    actions: List<OrgNavigationHeaderActionItem> = emptyList(),
    overflowItems: List<OrgOverflowMenuItem> = emptyList(),
    isOverflowExpanded: Boolean = false,
    onOverflowExpandedChange: (Boolean) -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Top + WindowInsetsSides.Horizontal))
            .height(SizingRoles.InteractionHeight.Comfort.dp)
            .padding(horizontal = PaddingRoles.Element.Compact.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        MolIconButton(role = IconButtonRole.Back, onTap = onBackTap, isOnScrim = true)
        Spacer(modifier = Modifier.weight(1f))
        actions.forEach { action ->
            MolIconButton(
                role = action.role,
                onTap = action.onTap,
                isActive = action.isActive,
                isOnScrim = true,
            )
        }
        OrgOverflowMenuButton(
            items = overflowItems,
            isExpanded = isOverflowExpanded,
            onExpandedChange = onOverflowExpandedChange,
            isOnScrim = true,
        )
    }
}
