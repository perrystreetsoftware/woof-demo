package com.perrystreet.woof.designsystem.atomic.atoms.placeholder.roles

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.perrystreet.woof.designsystem.atomic._tokens.spacing.PaddingRoles
import com.perrystreet.woof.designsystem.theme.Theme

enum class TagPlaceholderRole(val sampleText: String) {
    Expanded(sampleText = "Placeholder"),
    Regular(sampleText = "Sample tag"),
    Compact(sampleText = "Tag"),
    ;

    @Composable
    fun textStyle(): TextStyle = Theme.typography.subhead.p3

    @Composable
    fun radius(): Dp = Theme.radius.xl

    fun horizontalPadding(): PaddingRoles.Element = PaddingRoles.Element.Relaxed

    fun verticalPadding(): PaddingRoles.Element = PaddingRoles.Element.Compact
}
