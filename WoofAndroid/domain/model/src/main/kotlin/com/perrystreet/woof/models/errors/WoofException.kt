package com.perrystreet.woof.models.errors

sealed class WoofException : Throwable() {
    data object AlreadyWoofed : WoofException()
}
