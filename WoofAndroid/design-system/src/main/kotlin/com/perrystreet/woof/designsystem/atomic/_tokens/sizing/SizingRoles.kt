@file:Suppress("ktlint:standard:package-name")

package com.perrystreet.woof.designsystem.atomic._tokens.sizing

import androidx.compose.ui.unit.Dp
import com.perrystreet.woof.designsystem.atomic._primitives.sizing.SizingPrimitives

object SizingRoles {
    enum class Icon(
        val dp: Dp,
    ) {
        XS(SizingPrimitives.Size12),
        S(SizingPrimitives.Size16),
        M(SizingPrimitives.Size24),
        L(SizingPrimitives.Size32),
        XL(SizingPrimitives.Size44),
        XXL(SizingPrimitives.Size108),
    }

    enum class Avatar(
        val dp: Dp,
    ) {
        S(SizingPrimitives.Size40),
        M(SizingPrimitives.Size60),
        L(SizingPrimitives.Size88),
    }

    enum class HorizontalRule(
        val dp: Dp,
    ) {
        XS(SizingPrimitives.Size1),
        S(SizingPrimitives.Size2),
    }

    enum class InteractionHeight(
        val dp: Dp,
    ) {
        Compact(SizingPrimitives.Size32),
        Default(SizingPrimitives.Size48),
        Comfort(SizingPrimitives.Size56),
    }

    object GridCell {
        val MinWidth: Dp = SizingPrimitives.Size128
    }
}
