@file:Suppress("ktlint:standard:package-name")

package com.perrystreet.woof.designsystem.atomic._tokens.sizing

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import com.perrystreet.woof.designsystem.atomic._primitives.sizing.SizingPrimitives

@Immutable
data class SizingRoles(
    val radiusXS: Dp,
    val radiusS: Dp,
    val radiusM: Dp,
    val radiusL: Dp,
    val radiusXL: Dp,
    val horizontalRuleXS: Dp,
    val horizontalRuleS: Dp,
    val interactionHeightCompact: Dp,
    val interactionHeightDefault: Dp,
    val interactionHeightComfort: Dp,
    val heroSummaryMinHeight: Dp,
    val gridColumns: Int,
) {
    enum class Icon(val dp: Dp) {
        XS(SizingPrimitives.Size12),
        S(SizingPrimitives.Size16),
        M(SizingPrimitives.Size24),
        L(SizingPrimitives.Size32),
        XL(SizingPrimitives.Size44),
        XXL(SizingPrimitives.Size108),
    }

    enum class Avatar(val dp: Dp) {
        S(SizingPrimitives.Size40),
        M(SizingPrimitives.Size60),
        L(SizingPrimitives.Size88),
    }

    companion object {
        val Default = SizingRoles(
            radiusXS = SizingPrimitives.Size2,
            radiusS = SizingPrimitives.Size4,
            radiusM = SizingPrimitives.Size8,
            radiusL = SizingPrimitives.Size12,
            radiusXL = SizingPrimitives.Size20,
            horizontalRuleXS = SizingPrimitives.Size1,
            horizontalRuleS = SizingPrimitives.Size2,
            interactionHeightCompact = SizingPrimitives.Size32,
            interactionHeightDefault = SizingPrimitives.Size48,
            interactionHeightComfort = SizingPrimitives.Size56,
            heroSummaryMinHeight = SizingPrimitives.Size240,
            gridColumns = 3,
        )
    }
}
