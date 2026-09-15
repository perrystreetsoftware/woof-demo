package com.perrystreet.woof.designsystem.atomic.atoms.button.roles

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.perrystreet.woof.designsystem.theme.Theme

enum class ButtonBackgroundRole {
    None {
        @Composable
        override fun color(): Color = Color.Transparent
    },
    ScrimContainer {
        @Composable
        override fun color(): Color = Theme.colors.scrimContainer
    },
    ;

    @Composable
    abstract fun color(): Color
}
