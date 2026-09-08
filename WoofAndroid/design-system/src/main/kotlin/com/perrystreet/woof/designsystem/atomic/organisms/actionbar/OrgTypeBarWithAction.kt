package com.perrystreet.woof.designsystem.atomic.organisms.actionbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.perrystreet.woof.designsystem.atomic._tokens.spacing.SpacingRoles
import com.perrystreet.woof.designsystem.atomic.molecules.button.MolIconButton
import com.perrystreet.woof.designsystem.atomic.molecules.button.roles.IconButtonRole
import com.perrystreet.woof.designsystem.atomic.molecules.typebar.MolTypeBar
import com.perrystreet.woof.designsystem.theme.Theme

@Composable
fun OrgTypeBarWithAction(
    text: String,
    placeholder: String,
    onTextChange: (String) -> Unit,
    onSubmit: () -> Unit,
    actionRole: IconButtonRole,
    isActionActive: Boolean,
    onActionTap: () -> Unit,
    isSubmitEnabled: Boolean = text.isNotBlank(),
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = Theme.padding.elementRelaxed,
                vertical = Theme.padding.elementRegular,
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
        MolIconButton(
            role = actionRole,
            onTap = onActionTap,
            isActive = isActionActive,
            isOnScrim = true,
            hasBackground = true,
        )
    }
}
