package com.perrystreet.woof.presentation.account.ui

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.perrystreet.woof.designsystem.atomic.molecules.header.roles.SectionToneRole
import com.perrystreet.woof.designsystem.atomic.organisms.avatar.OrgAvatarRow
import com.perrystreet.woof.designsystem.atomic.organisms.header.OrgNavigationHeaderTitle
import com.perrystreet.woof.designsystem.atomic.organisms.section.OrgDetailsRow
import com.perrystreet.woof.designsystem.atomic.organisms.section.OrgDetailsSection
import com.perrystreet.woof.designsystem.atomic.organisms.section.OrgSectionsPlaceholder
import com.perrystreet.woof.designsystem.atomic.organisms.section.OrgTagsSection
import com.perrystreet.woof.designsystem.atomic.organisms.section.OrgTextSection
import com.perrystreet.woof.designsystem.atomic.templates.TemplateScrollableContent
import com.perrystreet.woof.designsystem.preview.PreviewDevices
import com.perrystreet.woof.designsystem.preview.ThemeProvider
import com.perrystreet.woof.designsystem.preview.ThemedScreenPreview
import com.perrystreet.woof.designsystem.theme.ITheme
import com.perrystreet.woof.presentation.account.ui.extensions.AccountDetailRowUIModelExtensions.label
import com.perrystreet.woof.presentation.account.ui.extensions.AccountDetailRowUIModelExtensions.value
import com.perrystreet.woof.presentation.account.ui.extensions.AccountSummaryUIModelExtensions.text
import com.perrystreet.woof.presentation.account.ui.preview.AccountPreviewData
import com.perrystreet.woof.presentation.account.uimodel.AccountUIModel
import com.perrystreet.woof.presentation.account.viewmodel.AccountViewModel
import com.perrystreet.woof.presentation.common.image.AsyncImageStateExtensions.rememberAsyncImageState
import com.perrystreet.woof.resources.R

@Composable
fun AccountScreen(state: AccountViewModel.State) {
    TemplateScrollableContent(topBar = { OrgNavigationHeaderTitle(title = stringResource(R.string.account_title)) }) {
        when (state) {
            AccountViewModel.State.Loading -> item(key = PlaceholderKey) { OrgSectionsPlaceholder() }
            is AccountViewModel.State.Loaded -> accountContent(account = state.account)
        }
    }
}

private fun LazyListScope.accountContent(account: AccountUIModel) {
    item(key = AvatarKey) {
        OrgAvatarRow(
            title = account.name,
            subtitle = account.summary.text(),
            imageState = rememberAsyncImageState(url = account.photoUrl),
            contentDescription = stringResource(R.string.accessibility_dog_photo, account.name),
        )
    }
    item(key = AboutKey) {
        OrgTextSection(
            title = stringResource(R.string.profile_section_about, account.name),
            text = account.bio,
            toneRole = SectionToneRole.OnSurface,
        )
    }
    item(key = PersonalityKey) {
        OrgTagsSection(
            title = stringResource(R.string.profile_section_personality),
            tags = account.personality,
            toneRole = SectionToneRole.OnSurface,
        )
    }
    item(key = DetailsKey) {
        OrgDetailsSection(
            title = stringResource(R.string.profile_section_details),
            rows = account.rows.map { row -> OrgDetailsRow(label = row.label(), value = row.value()) },
            toneRole = SectionToneRole.OnSurface,
        )
    }
}

private const val PlaceholderKey = "account_placeholder"
private const val AvatarKey = "account_avatar"
private const val AboutKey = "account_about"
private const val PersonalityKey = "account_personality"
private const val DetailsKey = "account_details"

@PreviewDevices
@Composable
private fun AccountScreenLoadedPreview(@PreviewParameter(ThemeProvider::class) theme: ITheme) {
    ThemedScreenPreview(theme = theme) {
        AccountScreen(state = AccountPreviewData.loaded())
    }
}

@PreviewDevices
@Composable
private fun AccountScreenLoadingPreview(@PreviewParameter(ThemeProvider::class) theme: ITheme) {
    ThemedScreenPreview(theme = theme) {
        AccountScreen(state = AccountViewModel.State.Loading)
    }
}
