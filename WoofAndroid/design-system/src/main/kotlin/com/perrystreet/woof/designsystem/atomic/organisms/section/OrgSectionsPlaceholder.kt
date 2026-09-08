package com.perrystreet.woof.designsystem.atomic.organisms.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.perrystreet.woof.designsystem.atomic._tokens.spacing.SpacingRoles
import com.perrystreet.woof.designsystem.atomic.molecules.placeholder.MolTextBlockPlaceholder

@Composable
fun OrgSectionsPlaceholder(sections: Int = 3) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(SpacingRoles.Module.Compact.dp),
    ) {
        repeat(sections) {
            MolTextBlockPlaceholder()
        }
    }
}
