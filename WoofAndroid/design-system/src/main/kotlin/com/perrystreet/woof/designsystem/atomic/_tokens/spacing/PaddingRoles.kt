@file:Suppress("ktlint:standard:package-name")

package com.perrystreet.woof.designsystem.atomic._tokens.spacing

import androidx.compose.ui.unit.Dp
import com.perrystreet.woof.designsystem.atomic._primitives.spacing.SpacingPrimitives

object PaddingRoles {
    enum class Screen(
        val dp: Dp,
    ) {
        ExtraCompact(SpacingPrimitives.Space4),
        Compact(SpacingPrimitives.Space8),
        Regular(SpacingPrimitives.Space20),
        Expanded(SpacingPrimitives.Space60),
    }

    enum class Element(
        val dp: Dp,
    ) {
        Compact(SpacingPrimitives.Space4),
        Regular(SpacingPrimitives.Space8),
        Relaxed(SpacingPrimitives.Space12),
        Expanded(SpacingPrimitives.Space20),
        ExtraExpanded(SpacingPrimitives.Space24),
    }
}
