@file:Suppress("ktlint:standard:package-name")

package com.perrystreet.woof.designsystem.atomic._tokens.spacing

import androidx.compose.ui.unit.Dp
import com.perrystreet.woof.designsystem.atomic._primitives.spacing.SpacingPrimitives

object SpacingRoles {
    enum class Component(val dp: Dp) {
        Hairline(SpacingPrimitives.Space2),
        ExtraCompact(SpacingPrimitives.Space4),
        Compact(SpacingPrimitives.Space8),
        Cozy(SpacingPrimitives.Space12),
        Regular(SpacingPrimitives.Space16),
        Relaxed(SpacingPrimitives.Space20),
        Expanded(SpacingPrimitives.Space24),
        ExtraExpanded(SpacingPrimitives.Space32),
    }

    enum class Module(val dp: Dp) {
        Compact(SpacingPrimitives.Space20),
        Regular(SpacingPrimitives.Space40),
        Expanded(SpacingPrimitives.Space60),
    }
}
