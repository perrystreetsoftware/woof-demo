package com.perrystreet.woof.designsystem.atomic.atoms.placeholder.roles

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import com.perrystreet.woof.designsystem.theme.Theme

enum class TextPlaceholderRole(val sampleText: String) {
    Title(sampleText = "Placeholder title") {
        @Composable
        override fun textStyle(): TextStyle = Theme.typography.display.h4
    },
    Subtitle(sampleText = "Placeholder subtitle text") {
        @Composable
        override fun textStyle(): TextStyle = Theme.typography.subhead.p2
    },
    Body(sampleText = "Placeholder body text that spans a line") {
        @Composable
        override fun textStyle(): TextStyle = Theme.typography.body.p1
    },
    ;

    @Composable
    abstract fun textStyle(): TextStyle
}
