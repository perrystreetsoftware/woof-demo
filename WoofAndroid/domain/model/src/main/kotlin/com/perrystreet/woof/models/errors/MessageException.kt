package com.perrystreet.woof.models.errors

sealed class MessageException : Throwable() {
    data object EmptyMessage : MessageException()
}
