package com.perrystreet.woof.designsystem.atomic.molecules.header.roles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.perrystreet.woof.designsystem.atomic._tokens.spacing.PaddingRoles
import com.perrystreet.woof.designsystem.atomic.atoms.tag.roles.TagStyleRole
import com.perrystreet.woof.designsystem.atomic.atoms.text.roles.TextColorRole
import com.perrystreet.woof.designsystem.theme.Theme

enum class SectionToneRole(
    val titleColorRole: TextColorRole,
    val bodyColorRole: TextColorRole,
    val labelColorRole: TextColorRole,
    val tagStyleRole: TagStyleRole,
) {
    OnScrim(
        titleColorRole = TextColorRole.OnScrim,
        bodyColorRole = TextColorRole.OnScrim,
        labelColorRole = TextColorRole.OnScrimVariant,
        tagStyleRole = TagStyleRole.OnScrim,
    ) {
        @Composable
        override fun Modifier.sectionContainer(): Modifier = this
    },
    OnSurface(
        titleColorRole = TextColorRole.OnSurface,
        bodyColorRole = TextColorRole.OnSurface,
        labelColorRole = TextColorRole.OnSurfaceVariant,
        tagStyleRole = TagStyleRole.Neutral,
    ) {
        @Composable
        override fun Modifier.sectionContainer(): Modifier = this
            .background(
                color = Theme.colors.surfaceContainer,
                shape = RoundedCornerShape(Theme.radius.l),
            )
            .padding(PaddingRoles.Element.Expanded.dp)
    },
    ;

    @Composable
    abstract fun Modifier.sectionContainer(): Modifier
}
