package com.perrystreet.woof.designsystem.atomic.atoms.placeholder

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.semantics.clearAndSetSemantics
import com.perrystreet.woof.designsystem.theme.Theme

@Composable
fun AtomTagPlaceholder(text: String) {
    Text(
        text = text,
        style = Theme.typography.subhead.p3,
        maxLines = 1,
        modifier = Modifier
            .background(
                color = Theme.colors.placeholder,
                shape = RoundedCornerShape(Theme.sizing.radiusXL),
            )
            .padding(
                horizontal = Theme.padding.elementRelaxed,
                vertical = Theme.padding.elementCompact,
            )
            .alpha(0f)
            .clearAndSetSemantics {},
    )
}
