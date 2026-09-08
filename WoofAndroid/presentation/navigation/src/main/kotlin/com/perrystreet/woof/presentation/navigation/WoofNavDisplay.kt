package com.perrystreet.woof.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import org.koin.compose.getKoin
import org.koin.compose.koinInject

@Composable
fun WoofNavDisplay(
    backStack: WoofBackStack = koinInject(),
    navigator: INavigator = koinInject(),
) {
    val koin = getKoin()
    val routerEntries = remember(koin) { koin.getAll<IRouterEntry>() }

    CompositionLocalProvider(LocalNavigator provides navigator) {
        NavDisplay(
            backStack = backStack.entries,
            onBack = { backStack.pop() },
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator(),
            ),
            entryProvider = entryProvider {
                routerEntries.forEach { routerEntry ->
                    with(routerEntry) { register() }
                }
            },
        )
    }
}
