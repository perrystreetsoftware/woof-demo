package com.perrystreet.woof.presentation.navigation

import org.koin.core.annotation.Single

@Single
class WoofNavigator(
    private val backStack: WoofBackStack,
) : INavigator {
    override fun goTo(destination: WoofDestination) {
        backStack.push(destination)
    }

    override fun back() {
        backStack.pop()
    }
}
