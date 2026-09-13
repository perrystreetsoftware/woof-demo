package com.perrystreet.woof.designsystem.atomic.atoms.background

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.perrystreet.woof.designsystem.theme.Theme

@Composable
fun AtomHeroScrim() {
    Spacer(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Theme.gradients.scrimVerticalDelayed),
    )
}
