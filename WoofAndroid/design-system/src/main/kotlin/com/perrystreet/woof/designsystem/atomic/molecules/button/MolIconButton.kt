package com.perrystreet.woof.designsystem.atomic.molecules.button

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import com.perrystreet.woof.designsystem.atomic._tokens.sizing.SizingRoles
import com.perrystreet.woof.designsystem.atomic.atoms.icon.AtomIcon
import com.perrystreet.woof.designsystem.atomic.atoms.icon.roles.IconColorRole
import com.perrystreet.woof.designsystem.atomic.molecules.button.roles.IconButtonRole
import com.perrystreet.woof.designsystem.theme.Theme

@Composable
fun MolIconButton(
    role: IconButtonRole,
    onTap: () -> Unit,
    modifier: Modifier = Modifier,
    isActive: Boolean = false,
    isOnScrim: Boolean = false,
    hasBackground: Boolean = false,
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
    val backgroundModifier = when (hasBackground) {
        true -> Modifier.background(Theme.colors.scrimContainer, CircleShape)
        false -> Modifier
    }
    val colorRole = when {
        isActive -> role.activeColorRole
        isOnScrim -> IconColorRole.OnScrim
        else -> IconColorRole.OnSurface
    }
    val iconRes = when (isActive) {
        true -> role.activeIconRes
        false -> role.iconRes
    }
    val contentDescriptionRes = when (isActive) {
        true -> role.activeContentDescriptionRes
        false -> role.contentDescriptionRes
    }

    IconButton(
        onClick = onTap,
        interactionSource = interactionSource,
        modifier = modifier
            .then(backgroundModifier)
            .scale(scale)
            .alpha(alpha),
    ) {
        AtomIcon(
            iconRes = iconRes,
            iconSize = SizingRoles.Icon.M,
            contentDescription = stringResource(contentDescriptionRes),
            colorRole = colorRole,
        )
    }
}

private const val PressedScale = 0.85f
