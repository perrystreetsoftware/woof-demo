package com.perrystreet.woof.designsystem.atomic.organisms.overflowmenu

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.DropdownMenu
import androidx.compose.runtime.Composable
import com.perrystreet.woof.designsystem.atomic.molecules.button.MolIconButton
import com.perrystreet.woof.designsystem.atomic.molecules.button.roles.IconButtonRole
import com.perrystreet.woof.designsystem.atomic.molecules.dropdown.MolDropdownMenuItem
import com.perrystreet.woof.designsystem.theme.Theme

@Composable
fun OrgOverflowMenuButton(
    items: List<OrgOverflowMenuItem>,
    isExpanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    isOnScrim: Boolean = false,
) {
    Box {
        MolIconButton(
            role = IconButtonRole.More,
            onTap = { onExpandedChange(true) },
            isOnScrim = isOnScrim,
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
