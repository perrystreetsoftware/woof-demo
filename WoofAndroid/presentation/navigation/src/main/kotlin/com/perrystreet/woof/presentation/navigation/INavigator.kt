package com.perrystreet.woof.presentation.navigation

interface INavigator {
    fun goTo(destination: WoofDestination)

    fun back()
}
