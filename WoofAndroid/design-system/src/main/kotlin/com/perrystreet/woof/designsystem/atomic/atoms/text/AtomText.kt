package com.perrystreet.woof.designsystem.atomic.atoms.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.perrystreet.woof.designsystem.atomic.atoms.text.roles.TextColorRole
import com.perrystreet.woof.designsystem.atomic.atoms.text.roles.TextFontRole

@Composable
fun AtomText(
    text: String,
    textFontRole: TextFontRole,
    modifier: Modifier = Modifier,
    colorRole: TextColorRole = TextColorRole.OnSurface,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    textAlign: TextAlign? = null,
) {
    Text(
        text = text,
        modifier = modifier,
        style = textFontRole.textStyle(),
        color = colorRole.color(),
        maxLines = maxLines,
        overflow = overflow,
        textAlign = textAlign,
    )
}
