package com.perrystreet.woof.designsystem.atomic.molecules.placeholder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.perrystreet.woof.designsystem.atomic._tokens.spacing.SpacingRoles
import com.perrystreet.woof.designsystem.atomic.atoms.placeholder.AtomTagPlaceholder
import com.perrystreet.woof.designsystem.atomic.atoms.placeholder.atomPlaceholderShimmer

@Composable
fun MolTagGroupPlaceholder() {
    Row(
        modifier = Modifier.atomPlaceholderShimmer(),
        horizontalArrangement = Arrangement.spacedBy(SpacingRoles.Component.Compact.dp),
    ) {
        SampleTags.forEach { tag ->
            AtomTagPlaceholder(text = tag)
        }
    }
}

private val SampleTags = listOf("Placeholder", "Sample tag", "Tag")
