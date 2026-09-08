package com.perrystreet.woof.datasource.messages

import org.koin.test.KoinTest
import org.koin.test.inject

class MessagesDataSourceFactory : KoinTest {
    private val dataSource: FakeMessagesDataSource by inject()

    fun withSendMessageError() = apply {
        dataSource.sendMessageError = IllegalStateException("Could not send message")
    }
}
