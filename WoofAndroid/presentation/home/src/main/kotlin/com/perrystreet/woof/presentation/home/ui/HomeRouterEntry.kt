package com.perrystreet.woof.presentation.home.ui

import androidx.navigation3.runtime.EntryProviderScope
import com.perrystreet.woof.presentation.navigation.IRouterEntry
import com.perrystreet.woof.presentation.navigation.WoofDestination
import org.koin.core.annotation.Factory

@Factory
class HomeRouterEntry : IRouterEntry {
    override fun EntryProviderScope<WoofDestination>.register() {
        entry<WoofDestination.Home> {
            HomeAdapter()
        }
    }
}
