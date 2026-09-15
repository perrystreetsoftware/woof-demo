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
import androidx.compose.ui.res.stringResource
import com.perrystreet.woof.designsystem.atomic._tokens.sizing.SizingRoles
import com.perrystreet.woof.designsystem.atomic._tokens.spacing.PaddingRoles
import com.perrystreet.woof.designsystem.atomic.atoms.button.AtomIconButton
import com.perrystreet.woof.designsystem.atomic.atoms.button.roles.ButtonBackgroundRole
import com.perrystreet.woof.designsystem.atomic.atoms.icon.roles.IconColorRole
import com.perrystreet.woof.designsystem.atomic.organisms.overflowmenu.OrgOverflowMenuButton
import com.perrystreet.woof.designsystem.atomic.organisms.overflowmenu.OrgOverflowMenuItem
import com.perrystreet.woof.resources.R

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
        AtomIconButton(
            iconRes = R.drawable.ic_arrow_back,
            contentDescription = stringResource(R.string.accessibility_back),
            colorRole = IconColorRole.OnScrim,
            backgroundRole = ButtonBackgroundRole.None,
            onTap = onBackTap,
        )
        Spacer(modifier = Modifier.weight(1f))
        actions.forEach { action ->
            AtomIconButton(
                iconRes = action.iconRes,
                contentDescription = action.contentDescription,
                colorRole = action.colorRole,
                backgroundRole = ButtonBackgroundRole.None,
                onTap = action.onTap,
            )
        }
        OrgOverflowMenuButton(
            items = overflowItems,
            isExpanded = isOverflowExpanded,
            onExpandedChange = onOverflowExpandedChange,
            colorRole = IconColorRole.OnScrim,
        )
    }
}
