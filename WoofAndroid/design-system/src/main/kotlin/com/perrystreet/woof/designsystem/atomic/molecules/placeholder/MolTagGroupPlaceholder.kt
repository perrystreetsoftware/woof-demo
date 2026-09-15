package com.perrystreet.woof.designsystem.atomic.molecules.placeholder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import com.perrystreet.woof.designsystem.atomic._tokens.spacing.SpacingRoles
import com.perrystreet.woof.designsystem.atomic.atoms.placeholder.AtomPlaceholder
import com.perrystreet.woof.designsystem.atomic.atoms.placeholder.roles.TagPlaceholderRole

@Composable
fun MolTagGroupPlaceholder() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(SpacingRoles.Component.Compact.dp),
    ) {
        SampleTagRoles.forEach { role ->
            AtomPlaceholder(role = role)
        }
    }
}

private val SampleTagRoles = listOf(
    TagPlaceholderRole.Expanded,
    TagPlaceholderRole.Regular,
    TagPlaceholderRole.Compact,
)
