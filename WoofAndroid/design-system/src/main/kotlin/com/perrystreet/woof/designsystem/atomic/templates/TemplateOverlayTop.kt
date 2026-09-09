package com.perrystreet.woof.designsystem.atomic.templates

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.perrystreet.woof.designsystem.atomic._tokens.spacing.PaddingRoles

@Composable
fun TemplateOverlayTop(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Top + WindowInsetsSides.Horizontal))
            .padding(
                top = PaddingRoles.Screen.Expanded.dp,
                start = PaddingRoles.Screen.Regular.dp,
                end = PaddingRoles.Screen.Regular.dp,
            ),
        contentAlignment = Alignment.TopCenter,
    ) {
        content()
    }
}
