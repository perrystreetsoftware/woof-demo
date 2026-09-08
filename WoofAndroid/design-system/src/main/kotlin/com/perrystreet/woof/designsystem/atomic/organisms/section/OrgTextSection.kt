package com.perrystreet.woof.designsystem.atomic.organisms.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.perrystreet.woof.designsystem.atomic._tokens.spacing.SpacingRoles
import com.perrystreet.woof.designsystem.atomic.atoms.text.AtomText
import com.perrystreet.woof.designsystem.atomic.atoms.text.roles.TextFontRole
import com.perrystreet.woof.designsystem.atomic.molecules.header.MolSectionTitle
import com.perrystreet.woof.designsystem.atomic.molecules.header.roles.SectionToneRole

@Composable
fun OrgTextSection(
    title: String,
    text: String,
    toneRole: SectionToneRole = SectionToneRole.OnScrim,
) {
    Column(
        modifier = with(toneRole) { Modifier.fillMaxWidth().sectionContainer() },
        verticalArrangement = Arrangement.spacedBy(SpacingRoles.Component.Cozy.dp),
    ) {
        MolSectionTitle(title = title, toneRole = toneRole)
        AtomText(
            text = text,
            textFontRole = TextFontRole.BodyP1,
            colorRole = toneRole.bodyColorRole,
        )
    }
}
