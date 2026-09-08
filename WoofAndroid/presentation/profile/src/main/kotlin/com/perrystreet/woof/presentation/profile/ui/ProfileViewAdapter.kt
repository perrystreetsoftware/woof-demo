package com.perrystreet.woof.presentation.profile.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import com.perrystreet.woof.presentation.common.error.ErrorAdapter
import com.perrystreet.woof.presentation.navigation.LocalNavigator
import com.perrystreet.woof.presentation.profile.ui.extensions.ProfileErrorToToastMapper
import com.perrystreet.woof.presentation.profile.uimodel.ProfileOverflowMenuItemUIModel
import com.perrystreet.woof.presentation.profile.uimodel.ProfilePageUIModel
import com.perrystreet.woof.presentation.profile.viewmodel.ProfileDetailsViewModel
import com.perrystreet.woof.presentation.profile.viewmodel.ProfileHeaderViewModel
import com.perrystreet.woof.presentation.profile.viewmodel.ProfileMessageViewModel
import com.perrystreet.woof.presentation.profile.viewmodel.ProfileModerationViewModel
import com.perrystreet.woof.presentation.profile.viewmodel.ProfileWoofViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun ProfileViewAdapter(
    page: ProfilePageUIModel,
    headerViewModel: ProfileHeaderViewModel = koinViewModel(key = "profile.header:${page.id}") { parametersOf(page.domain) },
    detailsViewModel: ProfileDetailsViewModel = koinViewModel(key = "profile.details:${page.id}") { parametersOf(page.domain) },
    woofViewModel: ProfileWoofViewModel = koinViewModel(key = "profile.woof:${page.id}") { parametersOf(page.domain) },
    messageViewModel: ProfileMessageViewModel = koinViewModel(key = "profile.message:${page.id}") { parametersOf(page.domain) },
    moderationViewModel: ProfileModerationViewModel = koinViewModel(key = "profile.moderation:${page.id}") { parametersOf(page.domain) },
) {
    val navigator = LocalNavigator.current
    val headerState by headerViewModel.state.subscribeAsState()
    val detailsState by detailsViewModel.state.subscribeAsState()
    val woofState by woofViewModel.state.subscribeAsState()
    val messageState by messageViewModel.state.subscribeAsState()
    val moderationState by moderationViewModel.state.subscribeAsState()

    LaunchedEffect(Unit) {
        headerViewModel.onViewAppear()
        detailsViewModel.onViewAppear()
        woofViewModel.onViewAppear()
        messageViewModel.onViewAppear()
        moderationViewModel.onViewAppear()
    }

    ProfileViewScreen(
        page = page,
        headerState = headerState,
        detailsState = detailsState,
        woofState = woofState,
        messageState = messageState,
        moderationState = moderationState,
        onBackTap = navigator::back,
        onFavoriteTap = headerViewModel::onFavoriteTap,
        onOverflowExpandedChange = headerViewModel::onOverflowExpandedChange,
        onOverflowItemTap = { item ->
            headerViewModel.onOverflowExpandedChange(false)
            when (item) {
                ProfileOverflowMenuItemUIModel.Report -> moderationViewModel.onReportTap()
                ProfileOverflowMenuItemUIModel.Block -> moderationViewModel.onBlockTap()
            }
        },
        onWoofTap = woofViewModel::onWoofTap,
        onWoofToastDismiss = woofViewModel::onToastDismiss,
        onMessageTextChange = messageViewModel::onTextChange,
        onMessageSendTap = messageViewModel::onSendTap,
        onMessageToastDismiss = messageViewModel::onToastDismiss,
        onModerationDialogConfirm = moderationViewModel::onDialogConfirm,
        onModerationDialogDismiss = moderationViewModel::onDialogDismiss,
        onModerationToastDismiss = moderationViewModel::onToastDismiss,
    )

    ErrorAdapter(
        viewModels = listOf(headerViewModel, detailsViewModel, woofViewModel, messageViewModel, moderationViewModel),
        errorMapper = ProfileErrorToToastMapper(name = page.name),
    )
}
