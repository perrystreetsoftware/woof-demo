package com.perrystreet.woof.designsystem.atomic.molecules.typebar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import com.perrystreet.woof.designsystem.atomic._tokens.sizing.SizingRoles
import com.perrystreet.woof.designsystem.atomic._tokens.spacing.PaddingRoles
import com.perrystreet.woof.designsystem.atomic.atoms.icon.AtomIcon
import com.perrystreet.woof.designsystem.atomic.atoms.icon.roles.IconColorRole
import com.perrystreet.woof.designsystem.atomic.atoms.text.AtomText
import com.perrystreet.woof.designsystem.atomic.atoms.text.roles.TextColorRole
import com.perrystreet.woof.designsystem.atomic.atoms.text.roles.TextFontRole
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
    val textStyle = Theme.typography.body.p1.copy(color = Theme.colors.onScrim)
    val submitColorRole = when (isSubmitEnabled) {
        true -> IconColorRole.Primary
        false -> IconColorRole.OnScrimVariant
    }

    BasicTextField(
        value = text,
        onValueChange = onTextChange,
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = SizingRoles.InteractionHeight.Default.dp)
            .clip(shape)
            .background(Theme.colors.scrimContainer),
        textStyle = textStyle,
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
        keyboardActions = KeyboardActions(onSend = { onSubmit() }),
        cursorBrush = SolidColor(Theme.colors.primary),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = PaddingRoles.Element.Expanded.dp,
                        end = PaddingRoles.Element.Relaxed.dp,
                        top = PaddingRoles.Element.Relaxed.dp,
                        bottom = PaddingRoles.Element.Relaxed.dp,
                    ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    when (text.isEmpty()) {
                        true -> AtomText(
                            text = placeholder,
                            textFontRole = TextFontRole.BodyP1,
                            colorRole = TextColorRole.OnScrimVariant,
                            maxLines = 1,
                        )
                        false -> Unit
                    }
                    innerTextField()
                }
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
        },
    )
}
