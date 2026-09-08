package com.perrystreet.woof.designsystem.preview

import androidx.compose.runtime.Composable
import com.perrystreet.woof.designsystem.theme.ITheme
import com.perrystreet.woof.designsystem.theme.Theme

@Composable
fun ThemedScreenPreview(theme: ITheme, content: @Composable () -> Unit) {
    Theme(theme = theme, content = content)
}
