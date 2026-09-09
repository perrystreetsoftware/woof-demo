package com.perrystreet.woof.designsystem.atomic.organisms.toast

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.perrystreet.woof.designsystem.atomic.molecules.toast.MolToast
import com.perrystreet.woof.designsystem.theme.Theme
import com.perrystreet.woof.utils.guard
import kotlinx.coroutines.delay

@Composable
fun OrgToastHost(
    message: String?,
    onDismiss: () -> Unit,
) {
    val durationMs = Theme.motion.toastDurationMs
    LaunchedEffect(message) {
        guard(message != null) { return@LaunchedEffect }
        delay(durationMs)
        onDismiss()
    }
    AnimatedVisibility(
        visible = message != null,
        enter = fadeIn() + slideInVertically(),
        exit = fadeOut() + slideOutVertically(),
    ) {
        MolToast(text = message.orEmpty())
    }
}
