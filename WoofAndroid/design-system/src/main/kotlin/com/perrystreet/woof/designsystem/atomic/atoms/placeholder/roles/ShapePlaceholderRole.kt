package com.perrystreet.woof.designsystem.atomic.atoms.placeholder.roles

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import com.perrystreet.woof.designsystem.theme.Theme

enum class ShapePlaceholderRole {
    Image {
        @Composable
        override fun radius(): Dp = Theme.radius.s

        @Composable
        override fun aspectRatio(): Float? = null
    },

    Card {
        @Composable
        override fun radius(): Dp = Theme.radius.s

        @Composable
        override fun aspectRatio(): Float = Theme.aspectRatios.gridCell
    },
    ;

    @Composable
    abstract fun radius(): Dp

    @Composable
    abstract fun aspectRatio(): Float?
}
