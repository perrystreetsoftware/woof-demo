package com.perrystreet.woof.designsystem.theme

import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import com.perrystreet.woof.designsystem.atomic._tokens.alpha.AlphaRoles
import com.perrystreet.woof.designsystem.atomic._tokens.aspectratio.AspectRatioRoles
import com.perrystreet.woof.designsystem.atomic._tokens.colors.Colors
import com.perrystreet.woof.designsystem.atomic._tokens.motion.MotionRoles
import com.perrystreet.woof.designsystem.atomic._tokens.radius.RadiusRoles
import com.perrystreet.woof.designsystem.atomic._tokens.typography.Typography

@Composable
fun Theme(theme: ITheme, content: @Composable () -> Unit) {
    val baseScheme = when (theme.isDark) {
        true -> darkColorScheme()
        false -> lightColorScheme()
    }
    MaterialTheme(
        colorScheme = baseScheme.copy(
            primary = theme.colors.primary,
            onPrimary = theme.colors.onPrimary,
            background = theme.colors.background,
            onBackground = theme.colors.onSurface,
            surface = theme.colors.surface,
            onSurface = theme.colors.onSurface,
            surfaceContainer = theme.colors.surfaceContainer,
            surfaceContainerHigh = theme.colors.surfaceContainerHigh,
            onSurfaceVariant = theme.colors.onSurfaceVariant,
            outlineVariant = theme.colors.outlineVariant,
            error = theme.colors.error,
            onError = theme.colors.onError,
        ),
        typography = MaterialTheme.typography.copy(
            headlineLarge = theme.typography.display.h1,
            headlineMedium = theme.typography.display.h2,
            headlineSmall = theme.typography.display.h3,
            titleLarge = theme.typography.subhead.p1,
            titleMedium = theme.typography.subhead.p2,
            titleSmall = theme.typography.subhead.p3,
            bodyLarge = theme.typography.body.p1,
            bodyMedium = theme.typography.body.p2,
            bodySmall = theme.typography.body.p3,
        ),
    ) {
        CompositionLocalProvider(
            LocalTheme provides theme,
            LocalTextStyle provides theme.typography.body.p1,
            LocalContentColor provides theme.colors.onSurface,
            content = content,
        )
    }
}

object Theme {
    val colors: Colors
        @Composable @ReadOnlyComposable
        get() = LocalTheme.current.colors

    val alpha: AlphaRoles
        @Composable @ReadOnlyComposable
        get() = LocalTheme.current.alpha

    val radius: RadiusRoles
        @Composable @ReadOnlyComposable
        get() = LocalTheme.current.radius

    val typography: Typography
        @Composable @ReadOnlyComposable
        get() = LocalTheme.current.typography

    val motion: MotionRoles
        @Composable @ReadOnlyComposable
        get() = LocalTheme.current.motion

    val aspectRatios: AspectRatioRoles
        @Composable @ReadOnlyComposable
        get() = LocalTheme.current.aspectRatios

    val isDark: Boolean
        @Composable @ReadOnlyComposable
        get() = LocalTheme.current.isDark
}

internal val LocalTheme = staticCompositionLocalOf<ITheme> { error("No theme provided") }
