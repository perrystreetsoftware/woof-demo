package com.perrystreet.woof.designsystem.atomic.templates

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.perrystreet.woof.designsystem.atomic._tokens.spacing.PaddingRoles
import com.perrystreet.woof.designsystem.theme.Theme

@Composable
fun TemplateCenteredContent(
    topBar: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    Scaffold(
        containerColor = Theme.colors.background,
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = {
            Box(modifier = Modifier.padding(horizontal = PaddingRoles.Screen.Regular.dp)) {
                topBar()
            }
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = PaddingRoles.Screen.Regular.dp),
            contentAlignment = Alignment.Center,
        ) {
            content()
        }
    }
}
