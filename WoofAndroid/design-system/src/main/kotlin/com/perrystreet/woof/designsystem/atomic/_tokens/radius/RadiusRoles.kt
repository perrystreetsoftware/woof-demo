@file:Suppress("ktlint:standard:package-name")

package com.perrystreet.woof.designsystem.atomic._tokens.radius

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import com.perrystreet.woof.designsystem.atomic._primitives.sizing.SizingPrimitives

@Immutable
data class RadiusRoles(
    val xs: Dp,
    val s: Dp,
    val m: Dp,
    val l: Dp,
    val xl: Dp,
) {
    companion object {
        val Default = RadiusRoles(
            xs = SizingPrimitives.Size2,
            s = SizingPrimitives.Size4,
            m = SizingPrimitives.Size8,
            l = SizingPrimitives.Size12,
            xl = SizingPrimitives.Size20,
        )
    }
}
