package com.perrystreet.woof.designsystem.atomic.molecules.button.roles

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.perrystreet.woof.designsystem.atomic.atoms.icon.roles.IconColorRole
import com.perrystreet.woof.resources.R

enum class IconButtonRole(
    @DrawableRes val iconRes: Int,
    @DrawableRes val activeIconRes: Int,
    @StringRes val contentDescriptionRes: Int,
    @StringRes val activeContentDescriptionRes: Int,
    val activeColorRole: IconColorRole,
) {
    Back(
        iconRes = R.drawable.ic_arrow_back,
        activeIconRes = R.drawable.ic_arrow_back,
        contentDescriptionRes = R.string.accessibility_back,
        activeContentDescriptionRes = R.string.accessibility_back,
        activeColorRole = IconColorRole.Primary,
    ),
    More(
        iconRes = R.drawable.ic_more_vertical,
        activeIconRes = R.drawable.ic_more_vertical,
        contentDescriptionRes = R.string.accessibility_more_options,
        activeContentDescriptionRes = R.string.accessibility_more_options,
        activeColorRole = IconColorRole.Primary,
    ),
    Favorite(
        iconRes = R.drawable.ic_star_outline,
        activeIconRes = R.drawable.ic_star_filled,
        contentDescriptionRes = R.string.accessibility_add_favorite,
        activeContentDescriptionRes = R.string.accessibility_remove_favorite,
        activeColorRole = IconColorRole.Recent,
    ),
    Woof(
        iconRes = R.drawable.ic_paw_outline,
        activeIconRes = R.drawable.ic_paw_filled,
        contentDescriptionRes = R.string.accessibility_woof,
        activeContentDescriptionRes = R.string.accessibility_woof,
        activeColorRole = IconColorRole.Primary,
    ),
    Send(
        iconRes = R.drawable.ic_send,
        activeIconRes = R.drawable.ic_send,
        contentDescriptionRes = R.string.accessibility_send_message,
        activeContentDescriptionRes = R.string.accessibility_send_message,
        activeColorRole = IconColorRole.Primary,
    ),
}
