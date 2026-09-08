package com.perrystreet.woof.designsystem.atomic.templates

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
        topBar = topBar,
    ) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(Theme.sizing.gridColumns),
            state = gridState,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(
                start = Theme.padding.elementCompact,
                end = Theme.padding.elementCompact,
                bottom = Theme.padding.screenBottomRegular,
            ),
            verticalArrangement = Arrangement.spacedBy(SpacingRoles.Component.ExtraCompact.dp),
            horizontalArrangement = Arrangement.spacedBy(SpacingRoles.Component.ExtraCompact.dp),
            content = content,
        )
    }
}
