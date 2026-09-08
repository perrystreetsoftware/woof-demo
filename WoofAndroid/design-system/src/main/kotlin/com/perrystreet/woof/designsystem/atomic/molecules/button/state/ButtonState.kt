package com.perrystreet.woof.designsystem.atomic.molecules.button.state

import androidx.compose.runtime.Composable
import com.perrystreet.woof.designsystem.theme.Theme

enum class ButtonState {
    Enabled {
        @Composable
        override fun alpha(): Float = Theme.alpha.enabled
        override val isClickable = true
        override val showsLoadingIndicator = false
    },
    Disabled {
        @Composable
        override fun alpha(): Float = Theme.alpha.disabled
        override val isClickable = false
        override val showsLoadingIndicator = false
    },
    Loading {
        @Composable
        override fun alpha(): Float = Theme.alpha.enabled
        override val isClickable = false
        override val showsLoadingIndicator = true
    },
    ;

    @Composable
    abstract fun alpha(): Float
    abstract val isClickable: Boolean
    abstract val showsLoadingIndicator: Boolean
}
