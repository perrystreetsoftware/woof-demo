package com.perrystreet.woof.designsystem.atomic.molecules.dropdown

import androidx.annotation.DrawableRes
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import com.perrystreet.woof.designsystem.atomic._tokens.sizing.SizingRoles
import com.perrystreet.woof.designsystem.atomic.atoms.icon.AtomIcon
import com.perrystreet.woof.designsystem.atomic.atoms.icon.roles.IconColorRole
import com.perrystreet.woof.designsystem.atomic.atoms.text.AtomText
import com.perrystreet.woof.designsystem.atomic.atoms.text.roles.TextColorRole
import com.perrystreet.woof.designsystem.atomic.atoms.text.roles.TextFontRole

@Composable
fun MolDropdownMenuItem(
    text: String,
    @DrawableRes iconRes: Int,
    onTap: () -> Unit,
    isDestructive: Boolean = false,
) {
    val textColorRole = when (isDestructive) {
        true -> TextColorRole.Destructive
        false -> TextColorRole.OnSurface
    }
    val iconColorRole = when (isDestructive) {
        true -> IconColorRole.Destructive
        false -> IconColorRole.OnSurface
    }
    DropdownMenuItem(
        text = { AtomText(text = text, textFontRole = TextFontRole.BodyP1, colorRole = textColorRole) },
        leadingIcon = { AtomIcon(iconRes = iconRes, iconSize = SizingRoles.Icon.M, colorRole = iconColorRole) },
        onClick = onTap,
    )
}
