package com.perrystreet.woof.designsystem.atomic.organisms.emptystate

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.perrystreet.woof.designsystem.atomic._tokens.spacing.SpacingRoles
import com.perrystreet.woof.designsystem.atomic.atoms.spacer.AtomSpacer
import com.perrystreet.woof.designsystem.atomic.molecules.button.MolButton

@Composable
fun OrgErrorState(
    title: String,
    message: String,
    actionText: String,
    onActionTap: () -> Unit,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        OrgEmptyState(title = title, message = message)
        AtomSpacer(spacing = SpacingRoles.Component.Expanded)
        MolButton(text = actionText, onTap = onActionTap)
    }
}
