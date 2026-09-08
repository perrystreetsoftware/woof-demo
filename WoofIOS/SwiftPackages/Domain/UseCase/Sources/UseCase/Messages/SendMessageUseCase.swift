import Combine
import DI
import Foundation
import Models
import Repositories

@Factory
public final class SendMessageUseCase {
    private let messagesRepository: MessagesRepository

    public func callAsFunction(dog: Dog, text: String) -> AnyPublisher<Void, MessageError> {
        let trimmed = text.trimmingCharacters(in: .whitespacesAndNewlines)
        return switch trimmed.isEmpty {
        case true: Fail(error: MessageError.emptyMessage).eraseToAnyPublisher()
        case false: messagesRepository.addMessage(dog: dog, text: trimmed)
        }
    }
}
