package com.perrystreet.woof.designsystem.atomic.atoms.placeholder.roles

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.perrystreet.woof.designsystem.theme.Theme

enum class TextPlaceholderRole(val sampleText: String) {
    Title(sampleText = "Placeholder title") {
        @Composable
        override fun textStyle(): TextStyle = Theme.typography.display.h4

        @Composable
        override fun radius(): Dp = Theme.radius.s
    },
    Subtitle(sampleText = "Placeholder subtitle text") {
        @Composable
        override fun textStyle(): TextStyle = Theme.typography.subhead.p2

        @Composable
        override fun radius(): Dp = Theme.radius.s
    },
    Body(sampleText = "Placeholder body text that spans a line") {
        @Composable
        override fun textStyle(): TextStyle = Theme.typography.body.p1

        @Composable
        override fun radius(): Dp = Theme.radius.s
    },
    ;

    @Composable
    abstract fun textStyle(): TextStyle

    @Composable
    abstract fun radius(): Dp
}
