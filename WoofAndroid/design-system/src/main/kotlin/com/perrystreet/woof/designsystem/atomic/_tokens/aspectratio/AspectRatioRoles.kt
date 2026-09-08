@file:Suppress("ktlint:standard:package-name")

package com.perrystreet.woof.designsystem.atomic._tokens.aspectratio

import androidx.compose.runtime.Immutable

@Immutable
data class AspectRatioRoles(
    val gridCell: Float,
    val square: Float,
) {
    companion object {
        val Default = AspectRatioRoles(
            gridCell = 0.75f,
            square = 1f,
        )
    }
}
