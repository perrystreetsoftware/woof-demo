package com.perrystreet.woof.designsystem.atomic.templates

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.perrystreet.woof.designsystem.atomic._tokens.sizing.SizingRoles
import com.perrystreet.woof.designsystem.atomic._tokens.spacing.PaddingRoles
import com.perrystreet.woof.designsystem.atomic._tokens.spacing.SpacingRoles
import com.perrystreet.woof.designsystem.theme.Theme

@Composable
fun TemplateGrid(
    topBar: @Composable () -> Unit,
    gridState: LazyGridState = rememberLazyGridState(),
    content: LazyGridScope.() -> Unit,
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
        LazyVerticalGrid(
            columns = GridCells.Adaptive(SizingRoles.GridCell.MinWidth),
            state = gridState,
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
            contentPadding =
                PaddingValues(
                    top = PaddingRoles.Screen.ExtraCompact.dp,
                    start = PaddingRoles.Screen.ExtraCompact.dp,
                    end = PaddingRoles.Screen.ExtraCompact.dp,
                    bottom = PaddingRoles.Screen.ExtraCompact.dp,
                ),
            verticalArrangement = Arrangement.spacedBy(SpacingRoles.Component.ExtraCompact.dp),
            horizontalArrangement = Arrangement.spacedBy(SpacingRoles.Component.ExtraCompact.dp),
            content = content,
        )
    }
}
