package com.perrystreet.woof.designsystem.atomic.organisms.section

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import com.perrystreet.woof.designsystem.atomic.atoms.divider.AtomHorizontalDivider
import com.perrystreet.woof.designsystem.atomic.molecules.header.MolSectionTitle
import com.perrystreet.woof.designsystem.atomic.molecules.header.roles.SectionToneRole
import com.perrystreet.woof.designsystem.atomic.molecules.info.MolLabeledValue

@Immutable
data class OrgDetailsRow(
    val label: String,
    val value: String,
)

@Composable
fun OrgDetailsSection(
    title: String,
    rows: List<OrgDetailsRow>,
    toneRole: SectionToneRole = SectionToneRole.OnScrim,
) {
    Column(modifier = with(toneRole) { Modifier.fillMaxWidth().sectionContainer() }) {
        MolSectionTitle(title = title, toneRole = toneRole)
        rows.forEachIndexed { index, row ->
            MolLabeledValue(label = row.label, value = row.value, toneRole = toneRole)
            when (index < rows.lastIndex) {
                true -> AtomHorizontalDivider()
                false -> Unit
            }
        }
    }
}
