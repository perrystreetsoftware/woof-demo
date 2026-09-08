package com.perrystreet.woof.designsystem.atomic.molecules.image.state

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.painter.Painter

@Immutable
data class AsyncImageState(
    val painter: Painter,
    val isLoading: Boolean,
)
