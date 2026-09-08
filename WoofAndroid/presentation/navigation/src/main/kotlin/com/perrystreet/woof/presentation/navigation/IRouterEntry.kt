package com.perrystreet.woof.presentation.navigation

import androidx.navigation3.runtime.EntryProviderScope

interface IRouterEntry {
    fun EntryProviderScope<WoofDestination>.register()
}
