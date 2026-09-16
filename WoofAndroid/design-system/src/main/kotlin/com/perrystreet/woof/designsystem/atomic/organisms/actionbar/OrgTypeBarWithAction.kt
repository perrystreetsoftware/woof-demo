package com.perrystreet.woof.designsystem.atomic.organisms.actionbar

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.perrystreet.woof.designsystem.atomic._tokens.spacing.PaddingRoles
import com.perrystreet.woof.designsystem.atomic._tokens.spacing.SpacingRoles
import com.perrystreet.woof.designsystem.atomic.atoms.button.AtomIconButton
import com.perrystreet.woof.designsystem.atomic.atoms.button.roles.ButtonBackgroundRole
import com.perrystreet.woof.designsystem.atomic.atoms.icon.roles.IconColorRole
import com.perrystreet.woof.designsystem.atomic.molecules.typebar.MolTypeBar

@Composable
fun OrgTypeBarWithAction(
    text: String,
    placeholder: String,
    onTextChange: (String) -> Unit,
    onSubmit: () -> Unit,
    @DrawableRes actionIconRes: Int,
    actionContentDescription: String,
    actionColorRole: IconColorRole,
    onActionTap: () -> Unit,
    isSubmitEnabled: Boolean = text.isNotBlank(),
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = PaddingRoles.Element.Relaxed.dp,
                vertical = PaddingRoles.Element.Regular.dp,
            ),
        horizontalArrangement = Arrangement.spacedBy(SpacingRoles.Component.Compact.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        MolTypeBar(
            text = text,
            placeholder = placeholder,
            onTextChange = onTextChange,
            onSubmit = onSubmit,
            isSubmitEnabled = isSubmitEnabled,
            modifier = Modifier.weight(1f),
        )
        AtomIconButton(
            iconRes = actionIconRes,
            contentDescription = actionContentDescription,
            colorRole = actionColorRole,
            backgroundRole = ButtonBackgroundRole.ScrimContainer,
            onTap = onActionTap,
        )
    }
}
