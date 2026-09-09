package com.perrystreet.woof.designsystem.atomic.atoms.loading

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.perrystreet.woof.designsystem.atomic._tokens.sizing.SizingRoles
import com.perrystreet.woof.designsystem.atomic.atoms.icon.roles.IconColorRole
import com.perrystreet.woof.designsystem.theme.Theme

@Composable
fun AtomCircularProgressIndicator(
    iconSize: SizingRoles.Icon = SizingRoles.Icon.M,
    colorRole: IconColorRole = IconColorRole.Primary,
) {
    CircularProgressIndicator(
        modifier = Modifier.size(iconSize.dp),
        color = colorRole.color(),
        strokeWidth = SizingRoles.HorizontalRule.S.dp,
        trackColor = Theme.colors.outlineVariant,
    )
}
