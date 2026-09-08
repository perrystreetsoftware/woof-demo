package com.perrystreet.woof.designsystem.atomic.molecules.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import com.perrystreet.woof.designsystem.atomic._tokens.sizing.SizingRoles
import com.perrystreet.woof.designsystem.atomic.atoms.loading.AtomCircularProgressIndicator
import com.perrystreet.woof.designsystem.atomic.atoms.text.AtomText
import com.perrystreet.woof.designsystem.atomic.atoms.text.roles.TextFontRole
import com.perrystreet.woof.designsystem.atomic.molecules.button.roles.ButtonRole
import com.perrystreet.woof.designsystem.atomic.molecules.button.state.ButtonState
import com.perrystreet.woof.designsystem.theme.Theme

@Composable
fun MolButton(
    text: String,
    onTap: () -> Unit,
    modifier: Modifier = Modifier,
    role: ButtonRole = ButtonRole.Primary,
    state: ButtonState = ButtonState.Enabled,
) {
    MolButtonContent(
        text = text,
        onTap = onTap,
        role = role,
        state = state,
        modifier = modifier.fillMaxWidth(),
        minHeight = Theme.sizing.interactionHeightComfort,
        textFontRole = TextFontRole.DisplayH4,
    )
}

@Composable
fun MolButtonCompact(
    text: String,
    onTap: () -> Unit,
    modifier: Modifier = Modifier,
    role: ButtonRole = ButtonRole.Primary,
    state: ButtonState = ButtonState.Enabled,
) {
    MolButtonContent(
        text = text,
        onTap = onTap,
        role = role,
        state = state,
        modifier = modifier,
        minHeight = Theme.sizing.interactionHeightDefault,
        textFontRole = TextFontRole.DisplayH5,
    )
}

@Composable
private fun MolButtonContent(
    text: String,
    onTap: () -> Unit,
    role: ButtonRole,
    state: ButtonState,
    modifier: Modifier,
    minHeight: androidx.compose.ui.unit.Dp,
    textFontRole: TextFontRole,
) {
    val shape = RoundedCornerShape(Theme.sizing.radiusM)
    Box(
        modifier = modifier
            .defaultMinSize(minHeight = minHeight)
            .alpha(state.alpha())
            .clip(shape)
            .background(color = role.surfaceColor(), shape = shape)
            .clickable(enabled = state.isClickable, onClick = onTap)
            .semantics(mergeDescendants = true) { this.role = Role.Button }
            .padding(horizontal = Theme.padding.elementExpanded),
        contentAlignment = Alignment.Center,
    ) {
        when (state.showsLoadingIndicator) {
            true -> AtomCircularProgressIndicator(
                iconSize = SizingRoles.Icon.M,
                colorRole = role.loadingColorRole,
            )
            false -> AtomText(
                text = text,
                textFontRole = textFontRole,
                colorRole = role.textColorRole,
                maxLines = 1,
                textAlign = TextAlign.Center,
            )
        }
    }
}
