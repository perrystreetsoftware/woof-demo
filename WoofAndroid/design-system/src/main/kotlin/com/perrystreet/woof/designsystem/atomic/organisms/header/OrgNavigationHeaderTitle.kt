package com.perrystreet.woof.designsystem.atomic.organisms.header

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.perrystreet.woof.designsystem.atomic.atoms.text.AtomText
import com.perrystreet.woof.designsystem.atomic.atoms.text.roles.TextFontRole
import com.perrystreet.woof.designsystem.theme.Theme

@Composable
fun OrgNavigationHeaderTitle(title: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Theme.colors.background)
            .statusBarsPadding()
            .height(Theme.sizing.interactionHeightComfort)
            .padding(horizontal = Theme.padding.screenHorizontal),
        contentAlignment = Alignment.CenterStart,
    ) {
        AtomText(text = title, textFontRole = TextFontRole.DisplayH2, maxLines = 1)
    }
}
