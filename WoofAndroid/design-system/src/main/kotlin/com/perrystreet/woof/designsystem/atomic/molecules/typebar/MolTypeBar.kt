package com.perrystreet.woof.designsystem.atomic.molecules.typebar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import com.perrystreet.woof.designsystem.atomic._tokens.sizing.SizingRoles
import com.perrystreet.woof.designsystem.atomic._tokens.spacing.PaddingRoles
import com.perrystreet.woof.designsystem.atomic.atoms.icon.AtomIcon
import com.perrystreet.woof.designsystem.atomic.atoms.icon.roles.IconColorRole
import com.perrystreet.woof.designsystem.atomic.atoms.text.roles.TextColorRole
import com.perrystreet.woof.designsystem.atomic.atoms.text.roles.TextFontRole
import com.perrystreet.woof.designsystem.atomic.atoms.textfield.AtomTextField
import com.perrystreet.woof.designsystem.theme.Theme
import com.perrystreet.woof.resources.R

@Composable
fun MolTypeBar(
    text: String,
    placeholder: String,
    onTextChange: (String) -> Unit,
    onSubmit: () -> Unit,
    modifier: Modifier = Modifier,
    isSubmitEnabled: Boolean = text.isNotBlank(),
) {
    val shape = RoundedCornerShape(Theme.radius.xl)
    val submitColorRole = when (isSubmitEnabled) {
        true -> IconColorRole.Primary
        false -> IconColorRole.OnScrimVariant
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = SizingRoles.InteractionHeight.Default.dp)
            .clip(shape)
            .background(Theme.colors.scrimContainer)
            .padding(
                start = PaddingRoles.Element.Expanded.dp,
                end = PaddingRoles.Element.Relaxed.dp,
                top = PaddingRoles.Element.Relaxed.dp,
                bottom = PaddingRoles.Element.Relaxed.dp,
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AtomTextField(
            text = text,
            placeholder = placeholder,
            onTextChange = onTextChange,
            onSubmit = onSubmit,
            textFontRole = TextFontRole.BodyP1,
            colorRole = TextColorRole.OnScrim,
            placeholderColorRole = TextColorRole.OnScrimVariant,
            modifier = Modifier.weight(1f),
        )
        AtomIcon(
            iconRes = R.drawable.ic_send,
            iconSize = SizingRoles.Icon.M,
            contentDescription = stringResource(R.string.accessibility_send_message),
            colorRole = submitColorRole,
            modifier = Modifier
                .semantics { role = Role.Button }
                .clickable(enabled = isSubmitEnabled, onClick = onSubmit),
        )
    }
}
