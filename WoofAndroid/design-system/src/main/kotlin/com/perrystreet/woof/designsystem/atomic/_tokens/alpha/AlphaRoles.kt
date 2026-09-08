@file:Suppress("ktlint:standard:package-name")

package com.perrystreet.woof.designsystem.atomic._tokens.alpha

import androidx.compose.runtime.Immutable
import com.perrystreet.woof.designsystem.atomic._primitives.colors.AlphaPrimitives

@Immutable
data class AlphaRoles(
    val enabled: Float,
    val disabled: Float,
    val pressed: Float,
    val heroDim: Float,
    val shimmerLow: Float,
    val shimmerLowMedium: Float,
) {
    companion object {
        val Default = AlphaRoles(
            enabled = AlphaPrimitives.Max,
            disabled = AlphaPrimitives.Medium,
            pressed = AlphaPrimitives.Medium,
            heroDim = AlphaPrimitives.High,
            shimmerLow = AlphaPrimitives.Low,
            shimmerLowMedium = AlphaPrimitives.LowMedium,
        )
    }
}
