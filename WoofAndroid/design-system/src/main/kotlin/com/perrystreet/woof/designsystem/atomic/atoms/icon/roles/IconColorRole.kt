package com.perrystreet.woof.designsystem.atomic.atoms.icon.roles

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.perrystreet.woof.designsystem.theme.Theme

enum class IconColorRole {
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
    Recent {
        @Composable
        override fun color(): Color = Theme.colors.recent
    },
    Destructive {
        @Composable
        override fun color(): Color = Theme.colors.destructive
    },
    ;

    @Composable
    abstract fun color(): Color
}
