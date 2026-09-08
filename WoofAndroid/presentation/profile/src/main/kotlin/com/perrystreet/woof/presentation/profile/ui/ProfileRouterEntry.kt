package com.perrystreet.woof.presentation.profile.ui

import androidx.navigation3.runtime.EntryProviderScope
import com.perrystreet.woof.presentation.navigation.IRouterEntry
import com.perrystreet.woof.presentation.navigation.WoofDestination
import org.koin.core.annotation.Factory

@Factory
class ProfileRouterEntry : IRouterEntry {
    override fun EntryProviderScope<WoofDestination>.register() {
        entry<WoofDestination.Profile> { destination ->
            ProfilePagerAdapter(dogId = destination.dogId)
        }
    }
}
