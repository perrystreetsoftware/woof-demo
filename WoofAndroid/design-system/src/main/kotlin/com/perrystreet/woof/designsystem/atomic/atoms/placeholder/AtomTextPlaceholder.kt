package com.perrystreet.woof.designsystem.atomic.atoms.placeholder

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.semantics.clearAndSetSemantics
import com.perrystreet.woof.designsystem.atomic.atoms.placeholder.roles.TextPlaceholderRole
import com.perrystreet.woof.designsystem.theme.Theme

@Composable
fun AtomTextPlaceholder(role: TextPlaceholderRole) {
    Box(
        modifier = Modifier.background(
            color = Theme.colors.placeholder,
            shape = RoundedCornerShape(Theme.sizing.radiusS),
        ),
    ) {
        Text(
            text = role.sampleText,
            style = role.textStyle(),
            maxLines = 1,
            modifier = Modifier
                .alpha(0f)
                .clearAndSetSemantics {},
        )
    }
}
