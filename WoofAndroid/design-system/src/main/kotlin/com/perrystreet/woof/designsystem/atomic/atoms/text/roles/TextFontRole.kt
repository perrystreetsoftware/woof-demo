package com.perrystreet.woof.designsystem.atomic.atoms.text.roles

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import com.perrystreet.woof.designsystem.theme.Theme

enum class TextFontRole {
    DisplayH1 {
        @Composable
        override fun textStyle(): TextStyle = Theme.typography.display.h1
    },
    DisplayH2 {
        @Composable
        override fun textStyle(): TextStyle = Theme.typography.display.h2
    },
    DisplayH3 {
        @Composable
        override fun textStyle(): TextStyle = Theme.typography.display.h3
    },
    DisplayH4 {
        @Composable
        override fun textStyle(): TextStyle = Theme.typography.display.h4
    },
    DisplayH5 {
        @Composable
        override fun textStyle(): TextStyle = Theme.typography.display.h5
    },
    DisplayH6 {
        @Composable
        override fun textStyle(): TextStyle = Theme.typography.display.h6
    },
    SubheadP1 {
        @Composable
        override fun textStyle(): TextStyle = Theme.typography.subhead.p1
    },
    SubheadP2 {
        @Composable
        override fun textStyle(): TextStyle = Theme.typography.subhead.p2
    },
    SubheadP3 {
        @Composable
        override fun textStyle(): TextStyle = Theme.typography.subhead.p3
    },
    BodyP1 {
        @Composable
        override fun textStyle(): TextStyle = Theme.typography.body.p1
    },
    BodyP2 {
        @Composable
        override fun textStyle(): TextStyle = Theme.typography.body.p2
    },
    BodyP3 {
        @Composable
        override fun textStyle(): TextStyle = Theme.typography.body.p3
    },
    BodyP4 {
        @Composable
        override fun textStyle(): TextStyle = Theme.typography.body.p4
    },
    ;

    @Composable
    abstract fun textStyle(): TextStyle
}
