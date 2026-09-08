package com.perrystreet.woof.designsystem.atomic.organisms.avatar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.perrystreet.woof.designsystem.atomic._tokens.sizing.SizingRoles
import com.perrystreet.woof.designsystem.atomic._tokens.spacing.SpacingRoles
import com.perrystreet.woof.designsystem.atomic.atoms.text.AtomText
import com.perrystreet.woof.designsystem.atomic.atoms.text.roles.TextColorRole
import com.perrystreet.woof.designsystem.atomic.atoms.text.roles.TextFontRole
import com.perrystreet.woof.designsystem.atomic.molecules.image.MolAsyncImage
import com.perrystreet.woof.designsystem.atomic.molecules.image.state.AsyncImageState

@Composable
fun OrgAvatarRow(
    title: String,
    subtitle: String,
    imageState: AsyncImageState,
    contentDescription: String,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(SpacingRoles.Component.Regular.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        MolAsyncImage(
            state = imageState,
            contentDescription = contentDescription,
            modifier = Modifier
                .size(SizingRoles.Avatar.L.dp)
                .clip(CircleShape),
        )
        Column(verticalArrangement = Arrangement.spacedBy(SpacingRoles.Component.ExtraCompact.dp)) {
            AtomText(text = title, textFontRole = TextFontRole.DisplayH2, maxLines = 1)
            AtomText(
                text = subtitle,
                textFontRole = TextFontRole.BodyP2,
                colorRole = TextColorRole.OnSurfaceVariant,
                maxLines = 2,
            )
        }
    }
}
