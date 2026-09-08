package com.perrystreet.woof.designsystem.atomic.organisms.header

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.perrystreet.woof.designsystem.atomic.molecules.header.MolBrandLogo
import com.perrystreet.woof.designsystem.theme.Theme

@Composable
fun OrgNavigationHeaderBranded() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Theme.colors.background)
            .statusBarsPadding()
            .height(Theme.sizing.interactionHeightComfort)
            .padding(horizontal = Theme.padding.screenHorizontal),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        MolBrandLogo()
    }
}
