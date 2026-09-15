package com.perrystreet.woof.designsystem.atomic.atoms.button

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import com.perrystreet.woof.designsystem.atomic._tokens.sizing.SizingRoles
import com.perrystreet.woof.designsystem.atomic.atoms.button.roles.ButtonBackgroundRole
import com.perrystreet.woof.designsystem.atomic.atoms.icon.roles.IconColorRole
import com.perrystreet.woof.designsystem.theme.Theme

@Composable
fun AtomIconButton(
    @DrawableRes iconRes: Int,
    contentDescription: String,
    colorRole: IconColorRole,
    backgroundRole: ButtonBackgroundRole,
    onTap: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val alpha by animateFloatAsState(
        targetValue = when (isPressed) {
            true -> Theme.alpha.pressed
            false -> Theme.alpha.enabled
        },
        label = "icon_button_alpha",
    )
    val scale by animateFloatAsState(
        targetValue = when (isPressed) {
            true -> PressedScale
            false -> 1f
        },
        label = "icon_button_scale",
    )
    IconButton(
        onClick = onTap,
        interactionSource = interactionSource,
        modifier = modifier
            .background(backgroundRole.color(), CircleShape)
            .scale(scale)
            .alpha(alpha),
    ) {
        Icon(
            painter = painterResource(iconRes),
            contentDescription = contentDescription,
            modifier = Modifier.size(SizingRoles.Icon.M.dp),
            tint = colorRole.color(),
        )
    }
}

private const val PressedScale = 0.85f
