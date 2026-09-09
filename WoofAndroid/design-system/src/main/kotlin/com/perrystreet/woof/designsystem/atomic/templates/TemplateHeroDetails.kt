package com.perrystreet.woof.designsystem.atomic.templates

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.unit.Constraints
import com.perrystreet.woof.designsystem.atomic._tokens.sizing.SizingRoles
import com.perrystreet.woof.designsystem.atomic._tokens.spacing.PaddingRoles
import com.perrystreet.woof.designsystem.atomic._tokens.spacing.SpacingRoles
import com.perrystreet.woof.designsystem.atomic.templates.base.HeroDetailsScrollConnection
import com.perrystreet.woof.designsystem.theme.Theme
import kotlin.math.roundToInt

@Composable
fun TemplateHeroDetails(
    hero: @Composable (dimProgress: () -> Float) -> Unit,
    topBar: @Composable () -> Unit,
    summary: @Composable () -> Unit,
    bottomBar: @Composable () -> Unit,
    overlay: @Composable () -> Unit,
    details: LazyListScope.() -> Unit,
) {
    val topInset = WindowInsets.safeDrawing.only(WindowInsetsSides.Top)
    val horizontalSafe = WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal)
    val topBarHeight = SizingRoles.InteractionHeight.Comfort.dp + topInset.asPaddingValues().calculateTopPadding()
    val panelHorizontalPadding = PaddingRoles.Screen.Regular.dp
    val panelBottomPadding = PaddingRoles.Screen.Regular.dp
    val bottomBarScrim = Theme.colors.scrimDim
    val listState = rememberLazyListState()
    val connection =
        remember {
            HeroDetailsScrollConnection(
                isListAtTop = { listState.firstVisibleItemIndex == 0 && listState.firstVisibleItemScrollOffset == 0 },
            )
        }
    val progress by remember { derivedStateOf { connection.progress } }

    SubcomposeLayout(
        modifier =
            Modifier
                .fillMaxSize()
                .background(Theme.colors.background),
    ) { constraints ->
        val width = constraints.maxWidth
        val height = constraints.maxHeight
        val loose = Constraints(maxWidth = width, maxHeight = height)

        val bottomBarPlaceables =
            subcompose(HeroDetailsSlot.BottomBar) {
                Box(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .background(Brush.verticalGradient(colors = listOf(Color.Transparent, bottomBarScrim)))
                            .imePadding()
                            .navigationBarsPadding()
                            .windowInsetsPadding(horizontalSafe),
                ) {
                    bottomBar()
                }
            }.map { it.measure(loose) }
        val bottomBarHeight = bottomBarPlaceables.maxOfOrNull { it.height } ?: 0
        val cutoutStart = horizontalSafe.getLeft(this, layoutDirection)
        val cutoutEnd = horizontalSafe.getRight(this, layoutDirection)
        val summaryWidth = (width - panelHorizontalPadding.roundToPx() * 2 - cutoutStart - cutoutEnd).coerceAtLeast(0)
        val summaryHeight =
            subcompose(HeroDetailsSlot.Summary, summary)
                .map { it.measure(Constraints(minWidth = summaryWidth, maxWidth = summaryWidth)) }
                .maxOfOrNull { it.height } ?: 0

        val minHeightPx = (summaryHeight + bottomBarHeight + panelBottomPadding.roundToPx()).toFloat()
        val maxHeightPx = (height - topBarHeight.roundToPx()).toFloat().coerceAtLeast(minHeightPx)
        connection.updateBounds(minHeightPx = minHeightPx, maxHeightPx = maxHeightPx)

        val panelHeight = connection.heightPx.coerceIn(minHeightPx, maxHeightPx).roundToInt()
        val bottomBarHeightDp = bottomBarHeight.toDp()

        val panelPlaceables =
            subcompose(HeroDetailsSlot.Panel) {
                LazyColumn(
                    state = listState,
                    modifier =
                        Modifier
                            .windowInsetsPadding(horizontalSafe)
                            .nestedScroll(connection),
                    verticalArrangement = Arrangement.spacedBy(SpacingRoles.Module.Compact.dp),
                    contentPadding =
                        PaddingValues(
                            start = panelHorizontalPadding,
                            end = panelHorizontalPadding,
                            bottom = bottomBarHeightDp + panelBottomPadding,
                        ),
                ) {
                    item(key = SummaryKey) {
                        summary()
                    }
                    item(key = BottomBarSpacerKey) {
                        Spacer(modifier = Modifier.height(bottomBarHeightDp * (1f - connection.progress)))
                    }
                    details()
                }
            }.map { it.measure(Constraints.fixed(width, panelHeight)) }

        val heroPlaceables = subcompose(HeroDetailsSlot.Hero) { hero({ progress }) }.map { it.measure(loose) }
        val topBarPlaceables = subcompose(HeroDetailsSlot.TopBar, topBar).map { it.measure(loose) }
        val overlayPlaceables = subcompose(HeroDetailsSlot.Overlay, overlay).map { it.measure(loose) }

        layout(width, height) {
            heroPlaceables.forEach { it.place(x = 0, y = 0) }
            panelPlaceables.forEach { it.place(x = 0, y = height - panelHeight) }
            topBarPlaceables.forEach { it.place(x = 0, y = 0) }
            bottomBarPlaceables.forEach { it.place(x = 0, y = height - it.height) }
            overlayPlaceables.forEach { it.place(x = 0, y = 0) }
        }
    }
}

private enum class HeroDetailsSlot {
    Hero,
    TopBar,
    Summary,
    Panel,
    BottomBar,
    Overlay,
}

private const val SummaryKey = "hero_details_summary"
private const val BottomBarSpacerKey = "hero_details_bottom_bar_spacer"
