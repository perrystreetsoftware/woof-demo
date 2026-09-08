package com.perrystreet.woof.designsystem.atomic.atoms.spacer

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.perrystreet.woof.designsystem.atomic._tokens.spacing.SpacingRoles

@Composable
fun AtomSpacer(spacing: SpacingRoles.Component) {
    Spacer(modifier = Modifier.size(spacing.dp))
}
