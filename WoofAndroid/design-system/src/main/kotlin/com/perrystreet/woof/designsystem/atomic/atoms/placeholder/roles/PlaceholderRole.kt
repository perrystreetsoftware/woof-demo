package com.perrystreet.woof.designsystem.atomic.atoms.placeholder.roles

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.perrystreet.woof.designsystem.atomic._tokens.spacing.PaddingRoles
import com.perrystreet.woof.designsystem.theme.Theme

enum class PlaceholderRole(
    val sampleText: String,
    val padding: PaddingValues,
) {
    Title(sampleText = "Placeholder title", padding = PaddingValues()) {
        @Composable
        override fun textStyle(): TextStyle = Theme.typography.display.h4

        @Composable
        override fun radius(): Dp = Theme.radius.s
    },
    Subtitle(sampleText = "Placeholder subtitle text", padding = PaddingValues()) {
        @Composable
        override fun textStyle(): TextStyle = Theme.typography.subhead.p2

        @Composable
        override fun radius(): Dp = Theme.radius.s
    },
    Body(sampleText = "Placeholder body text that spans a line", padding = PaddingValues()) {
        @Composable
        override fun textStyle(): TextStyle = Theme.typography.body.p1

        @Composable
        override fun radius(): Dp = Theme.radius.s
    },
    // Sized by its parent rather than by text: the caller passes the footprint it needs.
    Block(sampleText = "", padding = PaddingValues()) {
        @Composable
        override fun textStyle(): TextStyle = Theme.typography.body.p1

        @Composable
        override fun radius(): Dp = Theme.radius.s
    },
    // The grid cell the Browse feed lays out; the ratio matches OrgPhotoCard.
    Card(sampleText = "", padding = PaddingValues()) {
        @Composable
        override fun textStyle(): TextStyle = Theme.typography.body.p1

        @Composable
        override fun radius(): Dp = Theme.radius.s

        @Composable
        override fun aspectRatio(): Float = Theme.aspectRatios.gridCell
    },
    TagExpanded(
        sampleText = "Placeholder",
        padding = TagPadding,
    ) {
        @Composable
        override fun textStyle(): TextStyle = Theme.typography.subhead.p3

        @Composable
        override fun radius(): Dp = Theme.radius.xl
    },
    TagRegular(
        sampleText = "Sample tag",
        padding = TagPadding,
    ) {
        @Composable
        override fun textStyle(): TextStyle = Theme.typography.subhead.p3

        @Composable
        override fun radius(): Dp = Theme.radius.xl
    },
    TagCompact(
        sampleText = "Tag",
        padding = TagPadding,
    ) {
        @Composable
        override fun textStyle(): TextStyle = Theme.typography.subhead.p3

        @Composable
        override fun radius(): Dp = Theme.radius.xl
    },
    ;

    @Composable
    abstract fun textStyle(): TextStyle

    @Composable
    abstract fun radius(): Dp

    // Only roles whose footprint is a shape rather than text override this.
    @Composable
    open fun aspectRatio(): Float? = null
}

// A tag pill's inset, shared by the three sample widths.
private val TagPadding = PaddingValues(
    horizontal = PaddingRoles.Element.Relaxed.dp,
    vertical = PaddingRoles.Element.Compact.dp,
)
