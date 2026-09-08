package com.perrystreet.woof.presentation.home.ui.extensions

import com.perrystreet.woof.designsystem.atomic.organisms.navbar.roles.BottomNavigationRole
import com.perrystreet.woof.presentation.home.uimodel.HomeTabUIModel

object HomeTabUIModelExtensions {
    fun HomeTabUIModel.toBottomNavigationRole(): BottomNavigationRole = when (this) {
        HomeTabUIModel.Browse -> BottomNavigationRole.Browse
        HomeTabUIModel.Favorites -> BottomNavigationRole.Favorites
        HomeTabUIModel.Account -> BottomNavigationRole.Account
    }
}
