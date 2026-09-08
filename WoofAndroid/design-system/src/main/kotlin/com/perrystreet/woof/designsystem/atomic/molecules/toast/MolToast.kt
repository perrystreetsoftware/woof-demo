package com.perrystreet.woof.designsystem.atomic.molecules.toast

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import com.perrystreet.woof.designsystem.atomic._tokens.sizing.SizingRoles
import com.perrystreet.woof.designsystem.atomic._tokens.spacing.SpacingRoles
import com.perrystreet.woof.designsystem.atomic.atoms.icon.AtomIcon
import com.perrystreet.woof.designsystem.atomic.atoms.icon.roles.IconColorRole
import com.perrystreet.woof.designsystem.atomic.atoms.text.AtomText
import com.perrystreet.woof.designsystem.atomic.atoms.text.roles.TextFontRole
import com.perrystreet.woof.designsystem.theme.Theme
import com.perrystreet.woof.resources.R

@Composable
fun MolToast(text: String) {
    val shape = RoundedCornerShape(Theme.sizing.radiusL)
    Row(
        modifier = Modifier
            .shadow(elevation = Theme.sizing.radiusS, shape = shape)
            .background(color = Theme.colors.surfaceContainer, shape = shape)
            .padding(
                horizontal = Theme.padding.elementExpanded,
                vertical = Theme.padding.elementRelaxed,
            ),
        horizontalArrangement = Arrangement.spacedBy(SpacingRoles.Component.Cozy.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AtomIcon(
            iconRes = R.drawable.ic_check,
            iconSize = SizingRoles.Icon.S,
            colorRole = IconColorRole.Primary,
        )
        AtomText(
            text = text,
            textFontRole = TextFontRole.SubheadP2,
            maxLines = 2,
        )
    }
}
