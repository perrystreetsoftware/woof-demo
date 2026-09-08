package com.perrystreet.woof.designsystem.atomic.atoms.tag

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.perrystreet.woof.designsystem.atomic.atoms.tag.roles.TagStyleRole
import com.perrystreet.woof.designsystem.theme.Theme

@Composable
fun AtomTag(
    text: String,
    styleRole: TagStyleRole = TagStyleRole.Neutral,
) {
    Text(
        text = text,
        style = Theme.typography.subhead.p3,
        color = styleRole.textColor(),
        maxLines = 1,
        modifier = Modifier
            .background(
                color = styleRole.backgroundColor(),
                shape = RoundedCornerShape(Theme.sizing.radiusXL),
            )
            .padding(
                horizontal = Theme.padding.elementRelaxed,
                vertical = Theme.padding.elementCompact,
            ),
    )
}
