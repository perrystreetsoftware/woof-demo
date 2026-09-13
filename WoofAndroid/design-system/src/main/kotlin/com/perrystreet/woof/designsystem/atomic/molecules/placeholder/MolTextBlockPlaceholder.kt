package com.perrystreet.woof.designsystem.atomic.molecules.placeholder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.perrystreet.woof.designsystem.atomic._tokens.spacing.SpacingRoles
import com.perrystreet.woof.designsystem.atomic.atoms.placeholder.AtomPlaceholder
import com.perrystreet.woof.designsystem.atomic.atoms.placeholder.roles.PlaceholderRole
import com.perrystreet.woof.designsystem.atomic.atoms.spacer.AtomSpacer

@Composable
fun MolTextBlockPlaceholder(lines: Int = 3) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(SpacingRoles.Component.Compact.dp),
    ) {
        AtomPlaceholder(role = PlaceholderRole.Title)
        AtomSpacer(spacing = SpacingRoles.Component.ExtraCompact)
        repeat(lines) {
            AtomPlaceholder(role = PlaceholderRole.Body)
        }
    }
}
