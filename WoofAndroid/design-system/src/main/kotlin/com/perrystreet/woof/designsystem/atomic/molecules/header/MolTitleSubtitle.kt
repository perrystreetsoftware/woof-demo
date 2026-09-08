package com.perrystreet.woof.designsystem.atomic.molecules.header

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.perrystreet.woof.designsystem.atomic._tokens.spacing.SpacingRoles
import com.perrystreet.woof.designsystem.atomic.atoms.text.AtomText
import com.perrystreet.woof.designsystem.atomic.atoms.text.roles.TextColorRole
import com.perrystreet.woof.designsystem.atomic.atoms.text.roles.TextFontRole

@Composable
fun MolTitleSubtitle(
    title: String,
    subtitle: String,
) {
    Column(verticalArrangement = Arrangement.spacedBy(SpacingRoles.Component.ExtraCompact.dp)) {
        AtomText(
            text = title,
            textFontRole = TextFontRole.DisplayH1,
            colorRole = TextColorRole.OnScrim,
            maxLines = 1,
        )
        AtomText(
            text = subtitle,
            textFontRole = TextFontRole.SubheadP2,
            colorRole = TextColorRole.OnScrimVariant,
            maxLines = 2,
        )
    }
}
