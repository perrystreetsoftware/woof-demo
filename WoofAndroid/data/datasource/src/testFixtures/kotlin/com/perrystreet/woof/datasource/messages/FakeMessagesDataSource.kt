package com.perrystreet.woof.datasource.messages

import io.reactivex.rxjava3.core.Completable
import org.koin.core.annotation.Single

@Single
class FakeMessagesDataSource : IMessagesDataSource {
    val sentMessages = mutableListOf<Pair<Long, String>>()
    var sendMessageError: Throwable? = null

    override fun sendMessage(dogId: Long, text: String): Completable =
        sendMessageError?.let { Completable.error(it) }
            ?: Completable.fromAction { sentMessages.add(dogId to text) }
}
