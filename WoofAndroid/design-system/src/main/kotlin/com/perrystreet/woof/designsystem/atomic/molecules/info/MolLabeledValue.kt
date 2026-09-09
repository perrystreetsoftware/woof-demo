package com.perrystreet.woof.designsystem.atomic.molecules.info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.perrystreet.woof.designsystem.atomic._tokens.spacing.PaddingRoles
import com.perrystreet.woof.designsystem.atomic._tokens.spacing.SpacingRoles
import com.perrystreet.woof.designsystem.atomic.atoms.text.AtomText
import com.perrystreet.woof.designsystem.atomic.atoms.text.roles.TextFontRole
import com.perrystreet.woof.designsystem.atomic.molecules.header.roles.SectionToneRole

@Composable
fun MolLabeledValue(
    label: String,
    value: String,
    toneRole: SectionToneRole = SectionToneRole.OnScrim,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = PaddingRoles.Element.Relaxed.dp),
        horizontalArrangement = Arrangement.spacedBy(SpacingRoles.Component.Regular.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AtomText(
            text = label,
            textFontRole = TextFontRole.BodyP2,
            colorRole = toneRole.labelColorRole,
            maxLines = 1,
        )
        AtomText(
            text = value,
            textFontRole = TextFontRole.SubheadP2,
            colorRole = toneRole.bodyColorRole,
            textAlign = TextAlign.End,
            maxLines = 2,
            modifier = Modifier.weight(1f),
        )
    }
}
