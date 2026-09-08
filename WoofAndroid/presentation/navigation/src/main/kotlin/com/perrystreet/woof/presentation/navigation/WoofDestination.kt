package com.perrystreet.woof.presentation.navigation

sealed class WoofDestination {
    data object Home : WoofDestination()

    data class Profile(val dogId: Long) : WoofDestination()
}
