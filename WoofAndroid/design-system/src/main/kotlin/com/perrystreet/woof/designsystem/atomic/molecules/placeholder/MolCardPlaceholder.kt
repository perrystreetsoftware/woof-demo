package com.perrystreet.woof.designsystem.atomic.molecules.placeholder

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.perrystreet.woof.designsystem.atomic.atoms.placeholder.AtomRoundedRectanglePlaceholder
import com.perrystreet.woof.designsystem.atomic.atoms.placeholder.atomPlaceholderShimmer
import com.perrystreet.woof.designsystem.theme.Theme

@Composable
fun MolCardPlaceholder() {
    AtomRoundedRectanglePlaceholder(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(Theme.aspectRatios.gridCell)
            .atomPlaceholderShimmer(),
    )
}
