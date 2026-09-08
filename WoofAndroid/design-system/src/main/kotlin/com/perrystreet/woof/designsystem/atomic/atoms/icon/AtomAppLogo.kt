package com.perrystreet.woof.designsystem.atomic.atoms.icon

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.perrystreet.woof.designsystem.atomic._tokens.sizing.SizingRoles
import com.perrystreet.woof.designsystem.atomic.atoms.icon.roles.IconColorRole
import com.perrystreet.woof.resources.R

@Composable
fun AtomAppLogo(colorRole: IconColorRole = IconColorRole.Primary) {
    Icon(
        painter = painterResource(R.drawable.ic_logo),
        contentDescription = stringResource(R.string.accessibility_app_logo),
        modifier = Modifier.size(SizingRoles.Icon.L.dp),
        tint = colorRole.color(),
    )
}
