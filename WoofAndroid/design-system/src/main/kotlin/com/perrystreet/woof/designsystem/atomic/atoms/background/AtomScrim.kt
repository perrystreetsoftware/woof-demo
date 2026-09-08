package com.perrystreet.woof.designsystem.atomic.atoms.background

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import com.perrystreet.woof.designsystem.theme.Theme

@Composable
fun AtomScrim(alphaProvider: () -> Float) {
    val maxAlpha = Theme.alpha.heroDim
    Spacer(
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer { alpha = alphaProvider().coerceIn(0f, 1f) * maxAlpha }
            .background(Theme.colors.shadow),
    )
}
