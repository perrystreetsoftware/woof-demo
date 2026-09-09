package com.perrystreet.woof.designsystem.atomic.organisms.emptystate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.perrystreet.woof.designsystem.atomic._tokens.spacing.SpacingRoles
import com.perrystreet.woof.designsystem.atomic.atoms.icon.AtomAppLogo
import com.perrystreet.woof.designsystem.atomic.atoms.icon.roles.IconColorRole
import com.perrystreet.woof.designsystem.atomic.atoms.spacer.AtomSpacer
import com.perrystreet.woof.designsystem.atomic.atoms.text.AtomText
import com.perrystreet.woof.designsystem.atomic.atoms.text.roles.TextColorRole
import com.perrystreet.woof.designsystem.atomic.atoms.text.roles.TextFontRole

@Composable
fun OrgEmptyState(
    title: String,
    message: String,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        AtomAppLogo(colorRole = IconColorRole.OnSurfaceVariant)
        AtomSpacer(spacing = SpacingRoles.Component.Regular)
        AtomText(text = title, textFontRole = TextFontRole.DisplayH2, textAlign = TextAlign.Center)
        AtomSpacer(spacing = SpacingRoles.Component.Compact)
        AtomText(
            text = message,
            textFontRole = TextFontRole.BodyP1,
            colorRole = TextColorRole.OnSurfaceVariant,
            textAlign = TextAlign.Center,
        )
    }
}
