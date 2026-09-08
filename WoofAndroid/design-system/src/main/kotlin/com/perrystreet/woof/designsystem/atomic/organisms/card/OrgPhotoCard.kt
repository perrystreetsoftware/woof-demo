package com.perrystreet.woof.designsystem.atomic.organisms.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.perrystreet.woof.designsystem.atomic.atoms.background.AtomHeroScrim
import com.perrystreet.woof.designsystem.atomic.atoms.text.AtomText
import com.perrystreet.woof.designsystem.atomic.atoms.text.roles.TextColorRole
import com.perrystreet.woof.designsystem.atomic.atoms.text.roles.TextFontRole
import com.perrystreet.woof.designsystem.atomic.molecules.image.MolAsyncImage
import com.perrystreet.woof.designsystem.atomic.molecules.image.state.AsyncImageState
import com.perrystreet.woof.designsystem.theme.Theme

@Composable
fun OrgPhotoCard(
    title: String,
    imageState: AsyncImageState,
    contentDescription: String,
    onTap: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .aspectRatio(Theme.aspectRatios.gridCell)
            .clip(RoundedCornerShape(Theme.sizing.radiusS))
            .background(Theme.colors.placeholder)
            .clickable(onClick = onTap),
    ) {
        MolAsyncImage(
            state = imageState,
            contentDescription = contentDescription,
            modifier = Modifier.fillMaxSize(),
        )
        AtomHeroScrim()
        AtomText(
            text = title,
            textFontRole = TextFontRole.SubheadP2,
            colorRole = TextColorRole.OnScrim,
            maxLines = 1,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(Theme.padding.elementRegular),
        )
    }
}
