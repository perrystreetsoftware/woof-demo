package com.perrystreet.woof.designsystem.atomic.organisms.hero

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.perrystreet.woof.designsystem.atomic._tokens.spacing.SpacingRoles
import com.perrystreet.woof.designsystem.atomic.atoms.placeholder.AtomTextPlaceholder
import com.perrystreet.woof.designsystem.atomic.atoms.placeholder.atomPlaceholderShimmer
import com.perrystreet.woof.designsystem.atomic.atoms.placeholder.roles.TextPlaceholderRole
import com.perrystreet.woof.designsystem.atomic.atoms.text.AtomText
import com.perrystreet.woof.designsystem.atomic.atoms.text.roles.TextColorRole
import com.perrystreet.woof.designsystem.atomic.atoms.text.roles.TextFontRole
import com.perrystreet.woof.designsystem.atomic.molecules.header.MolTitleSubtitle
import com.perrystreet.woof.designsystem.atomic.molecules.placeholder.MolTagGroupPlaceholder
import com.perrystreet.woof.designsystem.atomic.molecules.tag.MolTagGroup

@Composable
fun OrgHeroSummary(
    title: String,
    subtitle: String?,
    tags: List<String>,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(SpacingRoles.Component.Cozy.dp),
    ) {
        when (subtitle) {
            null -> {
                OrgHeroSummaryTitleLoading(title = title)
                MolTagGroupPlaceholder()
            }
            else -> {
                MolTitleSubtitle(title = title, subtitle = subtitle)
                MolTagGroup(tags = tags)
            }
        }
    }
}

@Composable
private fun OrgHeroSummaryTitleLoading(title: String) {
    Column(verticalArrangement = Arrangement.spacedBy(SpacingRoles.Component.ExtraCompact.dp)) {
        AtomText(
            text = title,
            textFontRole = TextFontRole.DisplayH1,
            colorRole = TextColorRole.OnScrim,
            maxLines = 1,
        )
        Column(modifier = Modifier.atomPlaceholderShimmer()) {
            AtomTextPlaceholder(role = TextPlaceholderRole.Subtitle)
        }
    }
}
