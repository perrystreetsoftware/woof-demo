package com.perrystreet.woof.presentation.profile.ui

import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.perrystreet.woof.designsystem.atomic.molecules.button.roles.IconButtonRole
import com.perrystreet.woof.designsystem.atomic.organisms.actionbar.OrgTypeBarWithAction
import com.perrystreet.woof.designsystem.atomic.organisms.header.OrgNavigationHeaderActionItem
import com.perrystreet.woof.designsystem.atomic.organisms.header.OrgNavigationHeaderOverlay
import com.perrystreet.woof.designsystem.atomic.organisms.hero.OrgHeroPhoto
import com.perrystreet.woof.designsystem.atomic.organisms.hero.OrgHeroSummary
import com.perrystreet.woof.designsystem.atomic.organisms.section.OrgSectionsPlaceholder
import com.perrystreet.woof.designsystem.atomic.organisms.toast.OrgToastHost
import com.perrystreet.woof.designsystem.atomic.templates.TemplateOverlayTop
import com.perrystreet.woof.designsystem.atomic.templates.TemplateHeroDetails
import com.perrystreet.woof.designsystem.preview.PreviewDevices
import com.perrystreet.woof.designsystem.preview.ThemeProvider
import com.perrystreet.woof.designsystem.preview.ThemedScreenPreview
import com.perrystreet.woof.designsystem.theme.ITheme
import com.perrystreet.woof.presentation.common.image.AsyncImageStateExtensions.rememberAsyncImageState
import com.perrystreet.woof.presentation.profile.ui.components.ProfileModerationDialog
import com.perrystreet.woof.presentation.profile.ui.components.ProfileSection
import com.perrystreet.woof.presentation.profile.ui.extensions.ProfileOverflowMenuItemUIModelExtensions.toOverflowMenuItem
import com.perrystreet.woof.presentation.profile.ui.extensions.ProfileSummaryUIModelExtensions.text
import com.perrystreet.woof.presentation.profile.ui.extensions.ProfileToastUIModelExtensions.text
import com.perrystreet.woof.presentation.profile.ui.preview.ProfilePreviewData
import com.perrystreet.woof.presentation.profile.uimodel.ProfileContentUIModel
import com.perrystreet.woof.presentation.profile.uimodel.ProfileDetailsUIModel
import com.perrystreet.woof.presentation.profile.uimodel.ProfileOverflowMenuItemUIModel
import com.perrystreet.woof.presentation.profile.uimodel.ProfilePageUIModel
import com.perrystreet.woof.presentation.profile.viewmodel.ProfileHeaderViewModel
import com.perrystreet.woof.presentation.profile.viewmodel.ProfileMessageViewModel
import com.perrystreet.woof.presentation.profile.viewmodel.ProfileModerationViewModel
import com.perrystreet.woof.presentation.profile.viewmodel.ProfileWoofViewModel
import com.perrystreet.woof.resources.R

@Composable
fun ProfileViewScreen(
    page: ProfilePageUIModel,
    headerState: ProfileHeaderViewModel.State,
    detailsState: ProfileDetailsUIModel,
    woofState: ProfileWoofViewModel.State,
    messageState: ProfileMessageViewModel.State,
    moderationState: ProfileModerationViewModel.State,
    onBackTap: () -> Unit,
    onFavoriteTap: () -> Unit,
    onOverflowExpandedChange: (Boolean) -> Unit,
    onOverflowItemTap: (ProfileOverflowMenuItemUIModel) -> Unit,
    onWoofTap: () -> Unit,
    onWoofToastDismiss: () -> Unit,
    onMessageTextChange: (String) -> Unit,
    onMessageSendTap: () -> Unit,
    onMessageToastDismiss: () -> Unit,
    onModerationDialogConfirm: () -> Unit,
    onModerationDialogDismiss: () -> Unit,
    onModerationToastDismiss: () -> Unit,
) {
    TemplateHeroDetails(
        hero = { dimProgress ->
            OrgHeroPhoto(
                imageState = rememberAsyncImageState(url = page.photoUrl),
                contentDescription = stringResource(R.string.accessibility_dog_photo, page.name),
                dimProgress = dimProgress,
            )
        },
        topBar = {
            OrgNavigationHeaderOverlay(
                onBackTap = onBackTap,
                actions = listOf(
                    OrgNavigationHeaderActionItem(
                        role = IconButtonRole.Favorite,
                        onTap = onFavoriteTap,
                        isActive = headerState.isFavorite,
                    ),
                ),
                overflowItems = headerState.overflowItems.map { item ->
                    item.toOverflowMenuItem(onTap = { onOverflowItemTap(item) })
                },
                isOverflowExpanded = headerState.isOverflowExpanded,
                onOverflowExpandedChange = onOverflowExpandedChange,
            )
        },
        summary = {
            OrgHeroSummary(
                title = detailsState.name,
                subtitle = detailsState.summary?.text(),
                tags = detailsState.heroTags,
            )
        },
        bottomBar = {
            OrgTypeBarWithAction(
                text = messageState.text,
                placeholder = stringResource(R.string.profile_message_placeholder, page.name),
                onTextChange = onMessageTextChange,
                onSubmit = onMessageSendTap,
                isSubmitEnabled = messageState.isSendEnabled,
                actionRole = IconButtonRole.Woof,
                isActionActive = woofState.hasWoofed,
                onActionTap = onWoofTap,
            )
        },
        overlay = {
            TemplateOverlayTop {
                OrgToastHost(message = woofState.toast?.text(), onDismiss = onWoofToastDismiss)
                OrgToastHost(message = messageState.toast?.text(), onDismiss = onMessageToastDismiss)
                OrgToastHost(message = moderationState.toast?.text(), onDismiss = onModerationToastDismiss)
            }
            ProfileModerationDialog(
                dialog = moderationState.dialog,
                onConfirmTap = onModerationDialogConfirm,
                onDismissTap = onModerationDialogDismiss,
            )
        },
    ) {
        when (val content = detailsState.content) {
            ProfileContentUIModel.Loading -> item(key = PlaceholderKey) { OrgSectionsPlaceholder() }
            is ProfileContentUIModel.Visible -> items(content.sections) { section -> ProfileSection(section = section) }
        }
    }
}

private const val PlaceholderKey = "profile_sections_placeholder"

@PreviewDevices
@Composable
private fun ProfileViewScreenLoadedPreview(@PreviewParameter(ThemeProvider::class) theme: ITheme) {
    ThemedScreenPreview(theme = theme) {
        ProfileViewScreen(
            page = ProfilePreviewData.page(),
            headerState = ProfilePreviewData.header(),
            detailsState = ProfilePreviewData.details(),
            woofState = ProfileWoofViewModel.State(hasWoofed = true, toast = null),
            messageState = ProfileMessageViewModel.State.Initial,
            moderationState = ProfileModerationViewModel.State.Initial,
            onBackTap = {},
            onFavoriteTap = {},
            onOverflowExpandedChange = {},
            onOverflowItemTap = {},
            onWoofTap = {},
            onWoofToastDismiss = {},
            onMessageTextChange = {},
            onMessageSendTap = {},
            onMessageToastDismiss = {},
            onModerationDialogConfirm = {},
            onModerationDialogDismiss = {},
            onModerationToastDismiss = {},
        )
    }
}

@PreviewDevices
@Composable
private fun ProfileViewScreenLoadingPreview(@PreviewParameter(ThemeProvider::class) theme: ITheme) {
    ThemedScreenPreview(theme = theme) {
        ProfileViewScreen(
            page = ProfilePreviewData.page(),
            headerState = ProfilePreviewData.header(),
            detailsState = ProfilePreviewData.loadingDetails(),
            woofState = ProfileWoofViewModel.State(hasWoofed = false, toast = null),
            messageState = ProfileMessageViewModel.State.Initial,
            moderationState = ProfileModerationViewModel.State.Initial,
            onBackTap = {},
            onFavoriteTap = {},
            onOverflowExpandedChange = {},
            onOverflowItemTap = {},
            onWoofTap = {},
            onWoofToastDismiss = {},
            onMessageTextChange = {},
            onMessageSendTap = {},
            onMessageToastDismiss = {},
            onModerationDialogConfirm = {},
            onModerationDialogDismiss = {},
            onModerationToastDismiss = {},
        )
    }
}
