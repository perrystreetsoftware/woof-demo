package com.perrystreet.woof.presentation.profile.navigation

import com.perrystreet.woof.presentation.navigation.INavigator
import com.perrystreet.woof.presentation.navigation.WoofDestination

class FakeNavigator : INavigator {
    val destinations = mutableListOf<WoofDestination>()
    var backCount: Int = 0

    override fun goTo(destination: WoofDestination) {
        destinations += destination
    }

    override fun back() {
        backCount++
    }
}
