package com.perrystreet.woof.repositories.messages

import com.perrystreet.woof.datasource.messages.IMessagesDataSource
import com.perrystreet.woof.models.dog.Dog
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import org.koin.core.annotation.Single

@Single
class MessagesRepository(
    private val dataSource: IMessagesDataSource,
) {
    private val disposables = CompositeDisposable()

    fun addMessage(dog: Dog, text: String): Completable {
        val stream = dataSource.sendMessage(dog.id, text).cache()
        disposables += stream.subscribe({}, {})
        return stream
    }
}
