package com.perrystreet.woof.designsystem.atomic.atoms.placeholder

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import com.perrystreet.woof.designsystem.theme.Theme

@Composable
fun Modifier.atomPlaceholderShimmer(): Modifier {
    val highlight = Theme.colors.onPlaceholder
    val lowAlpha = Theme.alpha.shimmerLow
    val midAlpha = Theme.alpha.shimmerLowMedium
    val transition = rememberInfiniteTransition(label = "shimmer")
    val progress by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = Theme.motion.shimmerDurationMs, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
        label = "shimmer_progress",
    )
    return this
        .graphicsLayer { compositingStrategy = CompositingStrategy.Offscreen }
        .drawWithContent {
            drawContent()
            val sweep = size.width * 2
            val start = -size.width + progress * sweep
            drawRect(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color.Transparent,
                        highlight.copy(alpha = lowAlpha),
                        highlight.copy(alpha = midAlpha),
                        highlight.copy(alpha = lowAlpha),
                        Color.Transparent,
                    ),
                    start = Offset(start, 0f),
                    end = Offset(start + size.width, size.height),
                ),
                blendMode = BlendMode.SrcAtop,
            )
        }
}
