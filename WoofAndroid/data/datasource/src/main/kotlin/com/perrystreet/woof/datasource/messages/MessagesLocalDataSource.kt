package com.perrystreet.woof.datasource.messages

import io.reactivex.rxjava3.core.Completable
import org.koin.core.annotation.Single

@Single
class MessagesLocalDataSource : IMessagesDataSource {
    private val sentMessages = mutableMapOf<Long, MutableList<String>>()

    override fun sendMessage(dogId: Long, text: String): Completable =
        Completable.fromAction { sentMessages.getOrPut(dogId) { mutableListOf() }.add(text) }
}
