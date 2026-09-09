package com.perrystreet.woof.designsystem.preview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.perrystreet.woof.designsystem.atomic._tokens.spacing.PaddingRoles
import com.perrystreet.woof.designsystem.theme.ITheme
import com.perrystreet.woof.designsystem.theme.Theme

@Composable
fun ThemedPreview(theme: ITheme, content: @Composable () -> Unit) {
    Theme(theme = theme) {
        Box(
            modifier = Modifier
                .background(Theme.colors.background)
                .padding(PaddingRoles.Element.Regular.dp),
        ) {
            content()
        }
    }
}
