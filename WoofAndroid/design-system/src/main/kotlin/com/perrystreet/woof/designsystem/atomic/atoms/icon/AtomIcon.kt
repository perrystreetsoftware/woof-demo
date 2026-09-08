package com.perrystreet.woof.designsystem.atomic.atoms.icon

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.perrystreet.woof.designsystem.atomic._tokens.sizing.SizingRoles
import com.perrystreet.woof.designsystem.atomic.atoms.icon.roles.IconColorRole

@Composable
fun AtomIcon(
    @DrawableRes iconRes: Int,
    iconSize: SizingRoles.Icon,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    colorRole: IconColorRole = IconColorRole.OnSurface,
) {
    Icon(
        painter = painterResource(iconRes),
        contentDescription = contentDescription,
        modifier = modifier.size(iconSize.dp),
        tint = colorRole.color(),
    )
}
