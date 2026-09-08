package com.perrystreet.woof.presentation.profile.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.perrystreet.woof.designsystem.atomic.organisms.section.OrgDetailsRow
import com.perrystreet.woof.designsystem.atomic.organisms.section.OrgDetailsSection
import com.perrystreet.woof.designsystem.atomic.organisms.section.OrgTagsSection
import com.perrystreet.woof.designsystem.atomic.organisms.section.OrgTextSection
import com.perrystreet.woof.presentation.profile.ui.extensions.ProfileDetailRowUIModelExtensions.label
import com.perrystreet.woof.presentation.profile.ui.extensions.ProfileDetailRowUIModelExtensions.value
import com.perrystreet.woof.presentation.profile.uimodel.ProfileSectionUIModel
import com.perrystreet.woof.resources.R

@Composable
internal fun ProfileSection(section: ProfileSectionUIModel) {
    when (section) {
        is ProfileSectionUIModel.About -> OrgTextSection(
            title = stringResource(R.string.profile_section_about, section.name),
            text = section.bio,
        )
        is ProfileSectionUIModel.Personality -> OrgTagsSection(
            title = stringResource(R.string.profile_section_personality),
            tags = section.tags,
        )
        is ProfileSectionUIModel.Details -> OrgDetailsSection(
            title = stringResource(R.string.profile_section_details),
            rows = section.rows.map { row -> OrgDetailsRow(label = row.label(), value = row.value()) },
        )
    }
}
