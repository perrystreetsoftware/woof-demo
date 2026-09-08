package com.perrystreet.woof.designsystem.atomic.organisms.navbar

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.res.stringResource
import com.perrystreet.woof.designsystem.atomic._tokens.sizing.SizingRoles
import com.perrystreet.woof.designsystem.atomic.atoms.icon.AtomIcon
import com.perrystreet.woof.designsystem.atomic.atoms.icon.roles.IconColorRole
import com.perrystreet.woof.designsystem.atomic.atoms.text.AtomText
import com.perrystreet.woof.designsystem.atomic.atoms.text.roles.TextColorRole
import com.perrystreet.woof.designsystem.atomic.atoms.text.roles.TextFontRole
import com.perrystreet.woof.designsystem.atomic.organisms.navbar.roles.BottomNavigationRole
import com.perrystreet.woof.designsystem.theme.Theme

@Immutable
data class OrgBottomNavigationItem(
    val role: BottomNavigationRole,
    val isSelected: Boolean,
    val onTap: () -> Unit,
)

@Composable
fun OrgBottomNavigationBar(items: List<OrgBottomNavigationItem>) {
    NavigationBar(containerColor = Theme.colors.surfaceContainer) {
        items.forEach { item ->
            NavigationBarItem(
                selected = item.isSelected,
                onClick = item.onTap,
                icon = {
                    AtomIcon(
                        iconRes = item.iconRes(),
                        iconSize = SizingRoles.Icon.M,
                        colorRole = item.iconColorRole(),
                    )
                },
                label = {
                    AtomText(
                        text = stringResource(item.role.labelRes),
                        textFontRole = TextFontRole.SubheadP3,
                        colorRole = item.labelColorRole(),
                        maxLines = 1,
                    )
                },
                colors = NavigationBarItemDefaults.colors(indicatorColor = Theme.colors.primary),
            )
        }
    }
}

private fun OrgBottomNavigationItem.iconRes(): Int = when (isSelected) {
    true -> role.selectedIconRes
    false -> role.iconRes
}

private fun OrgBottomNavigationItem.iconColorRole(): IconColorRole = when (isSelected) {
    true -> IconColorRole.OnPrimary
    false -> IconColorRole.OnSurfaceVariant
}

private fun OrgBottomNavigationItem.labelColorRole(): TextColorRole = when (isSelected) {
    true -> TextColorRole.Primary
    false -> TextColorRole.OnSurfaceVariant
}
