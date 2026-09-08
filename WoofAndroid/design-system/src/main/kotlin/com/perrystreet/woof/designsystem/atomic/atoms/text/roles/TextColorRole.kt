package com.perrystreet.woof.designsystem.atomic.atoms.text.roles

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.perrystreet.woof.designsystem.theme.Theme

enum class TextColorRole {
    OnSurface {
        @Composable
        override fun color(): Color = Theme.colors.onSurface
    },
    OnSurfaceVariant {
        @Composable
        override fun color(): Color = Theme.colors.onSurfaceVariant
    },
    OnPrimary {
        @Composable
        override fun color(): Color = Theme.colors.onPrimary
    },
    OnScrim {
        @Composable
        override fun color(): Color = Theme.colors.onScrim
    },
    OnScrimVariant {
        @Composable
        override fun color(): Color = Theme.colors.onScrimVariant
    },
    Primary {
        @Composable
        override fun color(): Color = Theme.colors.primary
    },
    Destructive {
        @Composable
        override fun color(): Color = Theme.colors.destructive
    },
    ;

    @Composable
    abstract fun color(): Color
}
