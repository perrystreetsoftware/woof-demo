package com.perrystreet.woof.designsystem.atomic.atoms.tag.roles

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.perrystreet.woof.designsystem.theme.Theme

enum class TagStyleRole {
    Neutral {
        @Composable
        override fun backgroundColor(): Color = Theme.colors.surfaceContainerHigh

        @Composable
        override fun textColor(): Color = Theme.colors.onSurface
    },
    OnScrim {
        @Composable
        override fun backgroundColor(): Color = Theme.colors.scrimContainer

        @Composable
        override fun textColor(): Color = Theme.colors.onScrim
    },
    Accent {
        @Composable
        override fun backgroundColor(): Color = Theme.colors.primary

        @Composable
        override fun textColor(): Color = Theme.colors.onPrimary
    },
    ;

    @Composable
    abstract fun backgroundColor(): Color

    @Composable
    abstract fun textColor(): Color
}
