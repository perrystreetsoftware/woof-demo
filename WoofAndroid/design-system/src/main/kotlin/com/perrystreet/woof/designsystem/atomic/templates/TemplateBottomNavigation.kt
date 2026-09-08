package com.perrystreet.woof.designsystem.atomic.templates

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.perrystreet.woof.designsystem.theme.Theme

@Composable
fun TemplateBottomNavigation(
    bottomBar: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    Scaffold(
        containerColor = Theme.colors.background,
        bottomBar = bottomBar,
    ) { paddingValues ->
        val bottomPadding = PaddingValues(bottom = paddingValues.calculateBottomPadding())
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottomPadding)
                .consumeWindowInsets(bottomPadding),
        ) {
            content()
        }
    }
}
