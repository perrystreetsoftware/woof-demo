package com.perrystreet.woof.designsystem.atomic.molecules.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.minimumInteractiveComponentSize
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
import com.perrystreet.woof.designsystem.atomic._tokens.spacing.PaddingRoles
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
    val shape = RoundedCornerShape(Theme.radius.m)
    Box(
        modifier = modifier
            .minimumInteractiveComponentSize()
            .defaultMinSize(minHeight = SizingRoles.InteractionHeight.Default.dp)
            .alpha(state.alpha())
            .clip(shape)
            .background(color = role.surfaceColor(), shape = shape)
            .clickable(enabled = state.isClickable, onClick = onTap)
            .semantics(mergeDescendants = true) { this.role = Role.Button }
            .padding(horizontal = PaddingRoles.Element.Expanded.dp),
        contentAlignment = Alignment.Center,
    ) {
        when (state.showsLoadingIndicator) {
            true -> AtomCircularProgressIndicator(
                iconSize = SizingRoles.Icon.M,
                colorRole = role.loadingColorRole,
            )
            false -> AtomText(
                text = text,
                textFontRole = TextFontRole.DisplayH5,
                colorRole = role.textColorRole,
                maxLines = 1,
                textAlign = TextAlign.Center,
            )
        }
    }
}
