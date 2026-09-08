package com.perrystreet.woof.usecase.messages

import com.perrystreet.woof.models.dog.Dog
import com.perrystreet.woof.models.errors.MessageException
import com.perrystreet.woof.repositories.messages.MessagesRepository
import io.reactivex.rxjava3.core.Completable
import org.koin.core.annotation.Factory

@Factory
class SendMessageUseCase(
    private val messagesRepository: MessagesRepository,
) {
    operator fun invoke(dog: Dog, text: String): Completable {
        val trimmed = text.trim()
        return when (trimmed.isEmpty()) {
            true -> Completable.error(MessageException.EmptyMessage)
            false -> messagesRepository.addMessage(dog, trimmed)
        }
    }
}
