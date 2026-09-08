@file:Suppress("ktlint:standard:package-name")

package com.perrystreet.woof.designsystem.atomic._primitives.typography

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

object TypographyPrimitives {
    object Bold {
        val Size32 = style(size = 32, weight = FontWeight.Bold)
        val Size24 = style(size = 24, weight = FontWeight.Bold)
        val Size20 = style(size = 20, weight = FontWeight.Bold)
        val Size16 = style(size = 16, weight = FontWeight.Bold)
        val Size14 = style(size = 14, weight = FontWeight.Bold)
        val Size12 = style(size = 12, weight = FontWeight.Bold)
    }

    object SemiBold {
        val Size16 = style(size = 16, weight = FontWeight.SemiBold)
        val Size14 = style(size = 14, weight = FontWeight.SemiBold)
        val Size12 = style(size = 12, weight = FontWeight.SemiBold)
    }

    object Regular {
        val Size16 = style(size = 16, weight = FontWeight.Normal)
        val Size14 = style(size = 14, weight = FontWeight.Normal)
        val Size12 = style(size = 12, weight = FontWeight.Normal)
        val Size10 = style(size = 10, weight = FontWeight.Normal)
    }

    private fun style(size: Int, weight: FontWeight) = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = weight,
        fontSize = size.sp,
        lineHeight = 1.3.em,
    )
}
