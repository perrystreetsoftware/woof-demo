package com.perrystreet.woof.designsystem.atomic.templates

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import com.perrystreet.woof.designsystem.atomic._tokens.spacing.SpacingRoles
import com.perrystreet.woof.designsystem.atomic.templates.base.HeroDetailsScrollConnection
import com.perrystreet.woof.designsystem.theme.Theme

@Composable
fun TemplateHeroDetails(
    hero: @Composable (dimProgress: () -> Float) -> Unit,
    topBar: @Composable () -> Unit,
    summary: @Composable () -> Unit,
    bottomBar: @Composable () -> Unit,
    overlay: @Composable () -> Unit,
    details: LazyListScope.() -> Unit,
) {
    val density = LocalDensity.current
    val statusBarPadding = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    val topBarHeight = Theme.sizing.interactionHeightComfort + statusBarPadding
    val panelHorizontalPadding = Theme.padding.screenHorizontal
    val panelBottomPadding = Theme.padding.elementExpanded
    val bottomBarScrim = Theme.colors.scrimDim
    val defaultMinHeight = Theme.sizing.heroSummaryMinHeight

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.colors.background),
    ) {
        var bottomBarHeightPx by remember { mutableIntStateOf(0) }
        var summaryHeightPx by remember { mutableIntStateOf(0) }
        val listState = rememberLazyListState()
        val connection = remember {
            HeroDetailsScrollConnection(
                isListAtTop = { listState.firstVisibleItemIndex == 0 && listState.firstVisibleItemScrollOffset == 0 },
            )
        }
        val progress by remember { derivedStateOf { connection.progress } }
        val maxHeightPx = with(density) { (maxHeight - topBarHeight).toPx() }
        val panelBottomPaddingPx = with(density) { panelBottomPadding.toPx() }
        val defaultMinHeightPx = with(density) { defaultMinHeight.toPx() }

        LaunchedEffect(summaryHeightPx, bottomBarHeightPx, maxHeightPx) {
            val measuredMin = summaryHeightPx + bottomBarHeightPx + panelBottomPaddingPx
            val minHeightPx = when (summaryHeightPx > 0) {
                true -> measuredMin
                false -> defaultMinHeightPx
            }
            connection.updateBounds(minHeightPx = minHeightPx, maxHeightPx = maxHeightPx)
        }

        hero({ progress })

        LazyColumn(
            state = listState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(with(density) { connection.heightPx.toDp() })
                .nestedScroll(connection),
            verticalArrangement = Arrangement.spacedBy(SpacingRoles.Module.Compact.dp),
            contentPadding = PaddingValues(
                start = panelHorizontalPadding,
                end = panelHorizontalPadding,
                bottom = with(density) { bottomBarHeightPx.toDp() } + panelBottomPadding,
            ),
        ) {
            item(key = SummaryKey) {
                Box(modifier = Modifier.onSizeChanged { summaryHeightPx = it.height }) {
                    summary()
                }
            }
            item(key = BottomBarSpacerKey) {
                val spacerHeight = with(density) { (bottomBarHeightPx * (1f - connection.progress)).toDp() }
                Spacer(modifier = Modifier.height(spacerHeight))
            }
            details()
        }

        Box(modifier = Modifier.align(Alignment.TopCenter)) {
            topBar()
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .onSizeChanged { bottomBarHeightPx = it.height }
                .background(Brush.verticalGradient(colors = listOf(Color.Transparent, bottomBarScrim)))
                .imePadding()
                .navigationBarsPadding(),
        ) {
            bottomBar()
        }

        overlay()
    }
}

private const val SummaryKey = "hero_details_summary"
private const val BottomBarSpacerKey = "hero_details_bottom_bar_spacer"
