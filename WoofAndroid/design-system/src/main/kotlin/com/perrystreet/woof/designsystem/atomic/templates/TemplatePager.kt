package com.perrystreet.woof.designsystem.atomic.templates

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.perrystreet.woof.designsystem.theme.Theme

@Composable
fun TemplatePager(
    pageCount: Int,
    initialPage: Int,
    key: (page: Int) -> Any,
    pageContent: @Composable (page: Int) -> Unit,
) {
    val pagerState = rememberPagerState(initialPage = initialPage) { pageCount }

    HorizontalPager(
        state = pagerState,
        key = key,
        beyondViewportPageCount = 1,
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.colors.background),
    ) { page ->
        pageContent(page)
    }
}
