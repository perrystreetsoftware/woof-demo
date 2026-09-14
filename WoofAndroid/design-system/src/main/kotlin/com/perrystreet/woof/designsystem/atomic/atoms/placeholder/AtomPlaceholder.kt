package com.perrystreet.woof.designsystem.atomic.atoms.placeholder

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.clearAndSetSemantics
import com.perrystreet.woof.designsystem.atomic.atoms.placeholder.roles.PlaceholderRole
import com.perrystreet.woof.designsystem.theme.Theme
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.defaultShimmerTheme
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer

@Composable
fun AtomPlaceholder(
    role: PlaceholderRole,
    modifier: Modifier = Modifier,
) {
    val aspectRatio = role.aspectRatio()
    Box(
        modifier = modifier
            .then(aspectRatio?.let { Modifier.aspectRatio(it) } ?: Modifier)
            .placeholderShimmer()
            .background(
                color = Theme.colors.placeholder,
                shape = RoundedCornerShape(role.radius()),
            ),
    ) {
        Text(
            text = role.sampleText,
            style = role.textStyle(),
            maxLines = 1,
            modifier = Modifier
                .padding(role.padding)
                .alpha(0f)
                .clearAndSetSemantics {},
        )
    }
}

@Composable
private fun Modifier.placeholderShimmer(): Modifier {
    val highlight = Theme.colors.onPlaceholder
    val shimmer = rememberShimmer(
        shimmerBounds = ShimmerBounds.Window,
        theme = defaultShimmerTheme.copy(
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = Theme.motion.shimmerDurationMs, easing = LinearEasing),
                repeatMode = RepeatMode.Restart,
            ),
            blendMode = BlendMode.SrcAtop,
            shaderColors = listOf(
                Color.Transparent,
                highlight.copy(alpha = Theme.alpha.shimmerLow),
                highlight.copy(alpha = Theme.alpha.shimmerLowMedium),
                highlight.copy(alpha = Theme.alpha.shimmerLow),
                Color.Transparent,
            ),
            shaderColorStops = ShaderStops,
        ),
    )
    return this.shimmer(shimmer)
}

private val ShaderStops = listOf(0f, 0.25f, 0.5f, 0.75f, 1f)
