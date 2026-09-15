@file:Suppress("ktlint:standard:package-name")

package com.perrystreet.woof.designsystem.atomic._tokens.gradient

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.perrystreet.woof.designsystem.atomic._tokens.colors.Colors

@Immutable
data class GradientRoles(
    val scrimVerticalDelayed: Brush,
    val scrimVertical: Brush,
) {
    companion object {
        fun from(colors: Colors) = GradientRoles(
            scrimVertical = Brush.verticalGradient(
                colors = listOf(Color.Transparent, colors.scrimDim),
            ),
            scrimVerticalDelayed = Brush.verticalGradient(
                colorStops = arrayOf(
                    0f to Color.Transparent,
                    0.45f to Color.Transparent,
                    1f to colors.scrimDim,
                ),
            ),
        )
    }
}
