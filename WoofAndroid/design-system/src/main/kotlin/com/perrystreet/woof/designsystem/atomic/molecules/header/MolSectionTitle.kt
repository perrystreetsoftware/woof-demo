package com.perrystreet.woof.designsystem.atomic.molecules.header

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.perrystreet.woof.designsystem.atomic._tokens.spacing.SpacingRoles
import com.perrystreet.woof.designsystem.atomic.atoms.divider.AtomHorizontalDivider
import com.perrystreet.woof.designsystem.atomic.atoms.text.AtomText
import com.perrystreet.woof.designsystem.atomic.atoms.text.roles.TextFontRole
import com.perrystreet.woof.designsystem.atomic.molecules.header.roles.SectionToneRole

@Composable
fun MolSectionTitle(
    title: String,
    toneRole: SectionToneRole = SectionToneRole.OnScrim,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(SpacingRoles.Component.Compact.dp),
    ) {
        AtomText(
            text = title,
            textFontRole = TextFontRole.DisplayH4,
            colorRole = toneRole.titleColorRole,
            maxLines = 1,
        )
        AtomHorizontalDivider()
    }
}
