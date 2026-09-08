package com.perrystreet.woof.datasource.messages

import io.reactivex.rxjava3.core.Completable

interface IMessagesDataSource {
    fun sendMessage(dogId: Long, text: String): Completable
}
