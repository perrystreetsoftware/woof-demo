@file:Suppress("ktlint:standard:package-name")

package com.perrystreet.woof.designsystem.atomic._tokens.spacing

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import com.perrystreet.woof.designsystem.atomic._primitives.spacing.SpacingPrimitives

@Immutable
data class PaddingRoles(
    val screenHorizontal: Dp,
    val screenTopRegular: Dp,
    val screenBottomRegular: Dp,
    val elementCompact: Dp,
    val elementRegular: Dp,
    val elementRelaxed: Dp,
    val elementExpanded: Dp,
    val elementExtraExpanded: Dp,
) {
    companion object {
        val Default = PaddingRoles(
            screenHorizontal = SpacingPrimitives.Space20,
            screenTopRegular = SpacingPrimitives.Space60,
            screenBottomRegular = SpacingPrimitives.Space40,
            elementCompact = SpacingPrimitives.Space4,
            elementRegular = SpacingPrimitives.Space8,
            elementRelaxed = SpacingPrimitives.Space12,
            elementExpanded = SpacingPrimitives.Space20,
            elementExtraExpanded = SpacingPrimitives.Space24,
        )
    }
}
