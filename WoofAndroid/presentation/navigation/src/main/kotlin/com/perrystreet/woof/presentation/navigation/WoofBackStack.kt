package com.perrystreet.woof.presentation.navigation

import androidx.compose.runtime.mutableStateListOf
import org.koin.core.annotation.Single

@Single
class WoofBackStack {
    private val stack = mutableStateListOf<WoofDestination>(WoofDestination.Home)

    val entries: List<WoofDestination>
        get() = stack

    fun push(destination: WoofDestination) {
        stack += destination
    }

    fun pop() {
        if (stack.size > 1) {
            stack.removeAt(stack.lastIndex)
        }
    }
}
