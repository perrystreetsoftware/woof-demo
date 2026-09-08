package com.perrystreet.woof.designsystem.atomic.templates

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.perrystreet.woof.designsystem.atomic._tokens.spacing.SpacingRoles
import com.perrystreet.woof.designsystem.theme.Theme

@Composable
fun TemplateScrollableContent(
    topBar: @Composable () -> Unit,
    content: LazyListScope.() -> Unit,
) {
    Scaffold(
        containerColor = Theme.colors.background,
        topBar = topBar,
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(
                start = Theme.padding.screenHorizontal,
                end = Theme.padding.screenHorizontal,
                top = Theme.padding.elementRegular,
                bottom = Theme.padding.screenBottomRegular,
            ),
            verticalArrangement = Arrangement.spacedBy(SpacingRoles.Module.Compact.dp),
            content = content,
        )
    }
}
