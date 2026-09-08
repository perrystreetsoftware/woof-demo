package com.perrystreet.woof.designsystem.atomic.organisms.navbar.roles

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.perrystreet.woof.resources.R

enum class BottomNavigationRole(
    @DrawableRes val iconRes: Int,
    @DrawableRes val selectedIconRes: Int,
    @StringRes val labelRes: Int,
) {
    Browse(
        iconRes = R.drawable.ic_browse_outline,
        selectedIconRes = R.drawable.ic_browse_filled,
        labelRes = R.string.home_tab_browse,
    ),
    Favorites(
        iconRes = R.drawable.ic_star_outline,
        selectedIconRes = R.drawable.ic_star_filled,
        labelRes = R.string.home_tab_favorites,
    ),
    Account(
        iconRes = R.drawable.ic_account_outline,
        selectedIconRes = R.drawable.ic_account_filled,
        labelRes = R.string.home_tab_account,
    ),
}
