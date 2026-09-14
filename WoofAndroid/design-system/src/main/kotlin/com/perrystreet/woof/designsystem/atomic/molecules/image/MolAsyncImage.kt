package com.perrystreet.woof.designsystem.atomic.molecules.image

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.perrystreet.woof.designsystem.atomic.atoms.image.AtomPainterImage
import com.perrystreet.woof.designsystem.atomic.atoms.placeholder.AtomPlaceholder
import com.perrystreet.woof.designsystem.atomic.atoms.placeholder.roles.ShapePlaceholderRole
import com.perrystreet.woof.designsystem.atomic.molecules.image.state.AsyncImageState

@Composable
fun MolAsyncImage(
    state: AsyncImageState,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
) {
    Box(modifier = modifier) {
        AtomPainterImage(
            painter = state.painter,
            contentDescription = contentDescription,
            modifier = Modifier.fillMaxSize(),
            contentScale = contentScale,
        )
        AnimatedVisibility(visible = state.isLoading, exit = fadeOut()) {
            AtomPlaceholder(
                role = ShapePlaceholderRole.Image,
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}
