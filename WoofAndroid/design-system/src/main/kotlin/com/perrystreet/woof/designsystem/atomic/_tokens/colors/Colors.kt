@file:Suppress("ktlint:standard:package-name")

package com.perrystreet.woof.designsystem.atomic._tokens.colors

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class Colors(
    val background: Color,
    val surface: Color,
    val surfaceContainer: Color,
    val surfaceContainerHigh: Color,
    val surfaceContainerDisabled: Color,
    val onSurface: Color,
    val onSurfaceVariant: Color,
    val outlineVariant: Color,
    val primary: Color,
    val primaryHigh: Color,
    val onPrimary: Color,
    val scrim: Color,
    val scrimDim: Color,
    val onScrim: Color,
    val onScrimVariant: Color,
    val scrimContainer: Color,
    val active: Color,
    val recent: Color,
    val inactive: Color,
    val destructive: Color,
    val error: Color,
    val onError: Color,
    val success: Color,
    val placeholder: Color,
    val onPlaceholder: Color,
    val shadow: Color,
)
