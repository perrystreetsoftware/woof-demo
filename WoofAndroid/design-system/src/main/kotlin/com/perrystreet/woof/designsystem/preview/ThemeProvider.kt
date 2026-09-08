package com.perrystreet.woof.designsystem.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.perrystreet.woof.designsystem.theme.ITheme
import com.perrystreet.woof.designsystem.theme.WoofTheme

class ThemeProvider : PreviewParameterProvider<ITheme> {
    override val values = sequenceOf(WoofTheme.light(), WoofTheme.dark())
}
