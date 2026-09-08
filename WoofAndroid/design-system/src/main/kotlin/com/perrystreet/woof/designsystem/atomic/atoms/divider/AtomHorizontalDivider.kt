package com.perrystreet.woof.designsystem.atomic.atoms.divider

import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import com.perrystreet.woof.designsystem.theme.Theme

@Composable
fun AtomHorizontalDivider() {
    HorizontalDivider(
        thickness = Theme.sizing.horizontalRuleXS,
        color = Theme.colors.outlineVariant,
    )
}
