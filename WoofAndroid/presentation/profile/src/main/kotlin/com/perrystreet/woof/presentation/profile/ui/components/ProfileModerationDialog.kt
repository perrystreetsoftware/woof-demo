package com.perrystreet.woof.presentation.profile.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.perrystreet.woof.designsystem.atomic.organisms.dialog.OrgAlertDialog
import com.perrystreet.woof.presentation.profile.ui.extensions.ProfileModerationDialogUIModelExtensions.confirmRes
import com.perrystreet.woof.presentation.profile.ui.extensions.ProfileModerationDialogUIModelExtensions.messageRes
import com.perrystreet.woof.presentation.profile.ui.extensions.ProfileModerationDialogUIModelExtensions.titleRes
import com.perrystreet.woof.presentation.profile.uimodel.ProfileModerationDialogUIModel
import com.perrystreet.woof.resources.R
import com.perrystreet.woof.utils.guard

@Composable
internal fun ProfileModerationDialog(
    dialog: ProfileModerationDialogUIModel?,
    onConfirmTap: () -> Unit,
    onDismissTap: () -> Unit,
) {
    guard(dialog != null) { return }

    OrgAlertDialog(
        title = stringResource(dialog.titleRes(), dialog.name),
        message = stringResource(dialog.messageRes(), dialog.name),
        confirmText = stringResource(dialog.confirmRes()),
        dismissText = stringResource(R.string.profile_dialog_cancel),
        onConfirmTap = onConfirmTap,
        onDismissTap = onDismissTap,
        isDestructive = true,
    )
}
