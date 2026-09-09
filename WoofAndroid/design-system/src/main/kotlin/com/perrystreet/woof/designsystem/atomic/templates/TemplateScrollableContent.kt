package com.perrystreet.woof.designsystem.atomic.templates

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.perrystreet.woof.designsystem.atomic._tokens.spacing.PaddingRoles
import com.perrystreet.woof.designsystem.atomic._tokens.spacing.SpacingRoles
import com.perrystreet.woof.designsystem.theme.Theme

@Composable
fun TemplateScrollableContent(
    topBar: @Composable () -> Unit,
    content: LazyListScope.() -> Unit,
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
        LazyColumn(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
            contentPadding =
                PaddingValues(
                    start = PaddingRoles.Screen.Regular.dp,
                    end = PaddingRoles.Screen.Regular.dp,
                    top = PaddingRoles.Screen.Compact.dp,
                    bottom = PaddingRoles.Screen.Regular.dp,
                ),
            verticalArrangement = Arrangement.spacedBy(SpacingRoles.Module.Compact.dp),
            content = content,
        )
    }
}
