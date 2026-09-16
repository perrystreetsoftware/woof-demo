package com.perrystreet.woof.presentation.home.ui.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.perrystreet.woof.presentation.home.uimodel.HomeTabUIModel
import com.perrystreet.woof.resources.R

object HomeTabUIModelExtensions {
    fun HomeTabUIModel.iconRes(isSelected: Boolean): Int = when (this) {
        HomeTabUIModel.Browse -> when (isSelected) {
            true -> R.drawable.ic_browse_filled
            false -> R.drawable.ic_browse_outline
        }
        HomeTabUIModel.Favorites -> when (isSelected) {
            true -> R.drawable.ic_star_filled
            false -> R.drawable.ic_star_outline
        }
        HomeTabUIModel.Account -> when (isSelected) {
            true -> R.drawable.ic_account_filled
            false -> R.drawable.ic_account_outline
        }
    }

    @Composable
    fun HomeTabUIModel.label(): String = when (this) {
        HomeTabUIModel.Browse -> stringResource(R.string.home_tab_browse)
        HomeTabUIModel.Favorites -> stringResource(R.string.home_tab_favorites)
        HomeTabUIModel.Account -> stringResource(R.string.home_tab_account)
    }
}
