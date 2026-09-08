package com.perrystreet.woof.designsystem.atomic.atoms.background

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.perrystreet.woof.designsystem.theme.Theme

@Composable
fun AtomHeroScrim() {
    Spacer(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colorStops = arrayOf(
                        0f to Color.Transparent,
                        0.45f to Color.Transparent,
                        1f to Theme.colors.scrimDim,
                    ),
                ),
            ),
    )
}
