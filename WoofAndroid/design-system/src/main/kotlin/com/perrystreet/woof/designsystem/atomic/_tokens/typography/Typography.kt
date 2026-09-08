@file:Suppress("ktlint:standard:package-name")

package com.perrystreet.woof.designsystem.atomic._tokens.typography

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle

@Immutable
data class Typography(
    val display: Display,
    val subhead: Subhead,
    val body: Body,
) {
    @Immutable
    data class Display(
        val h1: TextStyle,
        val h2: TextStyle,
        val h3: TextStyle,
        val h4: TextStyle,
        val h5: TextStyle,
        val h6: TextStyle,
    )

    @Immutable
    data class Subhead(
        val p1: TextStyle,
        val p2: TextStyle,
        val p3: TextStyle,
    )

    @Immutable
    data class Body(
        val p1: TextStyle,
        val p2: TextStyle,
        val p3: TextStyle,
        val p4: TextStyle,
    )
}
