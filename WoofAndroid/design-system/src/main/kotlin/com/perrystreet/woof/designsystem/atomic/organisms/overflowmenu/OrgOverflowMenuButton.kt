package com.perrystreet.woof.designsystem.atomic.organisms.overflowmenu

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.DropdownMenu
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.perrystreet.woof.designsystem.atomic.atoms.button.AtomIconButton
import com.perrystreet.woof.designsystem.atomic.atoms.button.roles.ButtonBackgroundRole
import com.perrystreet.woof.designsystem.atomic.atoms.icon.roles.IconColorRole
import com.perrystreet.woof.designsystem.atomic.molecules.dropdown.MolDropdownMenuItem
import com.perrystreet.woof.designsystem.theme.Theme
import com.perrystreet.woof.resources.R

@Composable
fun OrgOverflowMenuButton(
    items: List<OrgOverflowMenuItem>,
    isExpanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    colorRole: IconColorRole,
) {
    Box {
        AtomIconButton(
            iconRes = R.drawable.ic_more_vertical,
            contentDescription = stringResource(R.string.accessibility_more_options),
            colorRole = colorRole,
            backgroundRole = ButtonBackgroundRole.None,
            onTap = { onExpandedChange(true) },
        )
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { onExpandedChange(false) },
            containerColor = Theme.colors.surfaceContainer,
        ) {
            items.forEach { item ->
                MolDropdownMenuItem(
                    text = item.text,
                    iconRes = item.iconRes,
                    isDestructive = item.isDestructive,
                    onTap = item.onTap,
                )
            }
        }
    }
}
