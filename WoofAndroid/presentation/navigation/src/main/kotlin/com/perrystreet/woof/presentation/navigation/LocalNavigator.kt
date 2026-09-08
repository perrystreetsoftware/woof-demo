package com.perrystreet.woof.presentation.navigation

import androidx.compose.runtime.staticCompositionLocalOf

val LocalNavigator = staticCompositionLocalOf<INavigator> { NoOpNavigator }

private object NoOpNavigator : INavigator {
    override fun goTo(destination: WoofDestination) = Unit

    override fun back() = Unit
}
