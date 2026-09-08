package com.perrystreet.woof.designsystem.atomic.molecules.button.roles

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.perrystreet.woof.designsystem.atomic.atoms.icon.roles.IconColorRole
import com.perrystreet.woof.designsystem.atomic.atoms.text.roles.TextColorRole
import com.perrystreet.woof.designsystem.theme.Theme

enum class ButtonRole {
    Primary {
        @Composable
        override fun surfaceColor(): Color = Theme.colors.primary
        override val textColorRole = TextColorRole.OnPrimary
        override val loadingColorRole = IconColorRole.OnPrimary
    },
    Secondary {
        @Composable
        override fun surfaceColor(): Color = Theme.colors.surfaceContainerHigh
        override val textColorRole = TextColorRole.OnSurface
        override val loadingColorRole = IconColorRole.OnSurface
    },
    Destructive {
        @Composable
        override fun surfaceColor(): Color = Theme.colors.destructive
        override val textColorRole = TextColorRole.OnPrimary
        override val loadingColorRole = IconColorRole.OnPrimary
    },
    ;

    @Composable
    abstract fun surfaceColor(): Color
    abstract val textColorRole: TextColorRole
    abstract val loadingColorRole: IconColorRole
}
