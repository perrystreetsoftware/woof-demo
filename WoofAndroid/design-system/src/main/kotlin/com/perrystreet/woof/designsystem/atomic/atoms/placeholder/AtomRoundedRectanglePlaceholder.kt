package com.perrystreet.woof.designsystem.atomic.atoms.placeholder

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.perrystreet.woof.designsystem.theme.Theme

@Composable
fun AtomRoundedRectanglePlaceholder(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.background(
            color = Theme.colors.placeholder,
            shape = RoundedCornerShape(Theme.radius.s),
        ),
    )
}
