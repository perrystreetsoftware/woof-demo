package com.perrystreet.woof.designsystem.atomic.organisms.hero

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.perrystreet.woof.designsystem.atomic.atoms.background.AtomHeroScrim
import com.perrystreet.woof.designsystem.atomic.atoms.background.AtomScrim
import com.perrystreet.woof.designsystem.atomic.molecules.image.MolAsyncImage
import com.perrystreet.woof.designsystem.atomic.molecules.image.state.AsyncImageState

@Composable
fun OrgHeroPhoto(
    imageState: AsyncImageState,
    contentDescription: String,
    dimProgress: () -> Float,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        MolAsyncImage(
            state = imageState,
            contentDescription = contentDescription,
            modifier = Modifier.fillMaxSize(),
        )
        AtomHeroScrim()
        AtomScrim(alphaProvider = dimProgress)
    }
}
