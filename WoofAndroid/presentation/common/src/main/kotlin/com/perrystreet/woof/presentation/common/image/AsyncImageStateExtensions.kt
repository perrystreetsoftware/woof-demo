package com.perrystreet.woof.presentation.common.image

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import com.perrystreet.woof.designsystem.atomic.molecules.image.state.AsyncImageState

object AsyncImageStateExtensions {
    @Composable
    fun rememberAsyncImageState(url: String): AsyncImageState {
        val painter = rememberAsyncImagePainter(model = url)
        val painterState by painter.state.collectAsState()
        return AsyncImageState(
            painter = painter,
            isLoading = painterState is AsyncImagePainter.State.Loading ||
                painterState is AsyncImagePainter.State.Empty,
        )
    }
}
