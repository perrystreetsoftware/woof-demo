package com.perrystreet.woof.designsystem.atomic.organisms.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import com.perrystreet.woof.designsystem.atomic.atoms.text.AtomText
import com.perrystreet.woof.designsystem.atomic.atoms.text.roles.TextColorRole
import com.perrystreet.woof.designsystem.atomic.atoms.text.roles.TextFontRole
import com.perrystreet.woof.designsystem.atomic.molecules.button.MolButtonCompact
import com.perrystreet.woof.designsystem.atomic.molecules.button.roles.ButtonRole
import com.perrystreet.woof.designsystem.theme.Theme

@Composable
fun OrgAlertDialog(
    title: String,
    message: String,
    confirmText: String,
    dismissText: String,
    onConfirmTap: () -> Unit,
    onDismissTap: () -> Unit,
    isDestructive: Boolean = false,
) {
    val confirmRole = when (isDestructive) {
        true -> ButtonRole.Destructive
        false -> ButtonRole.Primary
    }
    AlertDialog(
        onDismissRequest = onDismissTap,
        containerColor = Theme.colors.surfaceContainer,
        title = { AtomText(text = title, textFontRole = TextFontRole.DisplayH3) },
        text = {
            AtomText(
                text = message,
                textFontRole = TextFontRole.BodyP1,
                colorRole = TextColorRole.OnSurfaceVariant,
            )
        },
        confirmButton = { MolButtonCompact(text = confirmText, onTap = onConfirmTap, role = confirmRole) },
        dismissButton = { MolButtonCompact(text = dismissText, onTap = onDismissTap, role = ButtonRole.Secondary) },
    )
}
