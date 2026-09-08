package com.perrystreet.woof.designsystem.atomic.molecules.header

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import com.perrystreet.woof.designsystem.atomic._tokens.spacing.SpacingRoles
import com.perrystreet.woof.designsystem.atomic.atoms.icon.AtomAppLogo
import com.perrystreet.woof.designsystem.atomic.atoms.text.AtomText
import com.perrystreet.woof.designsystem.atomic.atoms.text.roles.TextFontRole
import com.perrystreet.woof.resources.R

@Composable
fun MolBrandLogo() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(SpacingRoles.Component.Compact.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AtomAppLogo()
        AtomText(
            text = stringResource(R.string.app_name),
            textFontRole = TextFontRole.DisplayH2,
            maxLines = 1,
        )
    }
}
