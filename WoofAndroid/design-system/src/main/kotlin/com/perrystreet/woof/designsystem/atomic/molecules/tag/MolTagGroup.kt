package com.perrystreet.woof.designsystem.atomic.molecules.tag

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.perrystreet.woof.designsystem.atomic._tokens.spacing.SpacingRoles
import com.perrystreet.woof.designsystem.atomic.atoms.tag.AtomTag
import com.perrystreet.woof.designsystem.atomic.atoms.tag.roles.TagStyleRole

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MolTagGroup(
    tags: List<String>,
    styleRole: TagStyleRole = TagStyleRole.OnScrim,
) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(SpacingRoles.Component.Compact.dp),
        verticalArrangement = Arrangement.spacedBy(SpacingRoles.Component.Compact.dp),
    ) {
        tags.forEach { tag ->
            AtomTag(text = tag, styleRole = styleRole)
        }
    }
}
