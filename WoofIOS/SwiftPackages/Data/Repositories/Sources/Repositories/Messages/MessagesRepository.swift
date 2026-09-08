import Combine
import DI
import DataSource
import Models
import Utils

@Single
public final class MessagesRepository {
    private let dataSource: MessagesDataSourceImplementing

    private var cancellables = Set<AnyCancellable>()

    public func addMessage(dog: Dog, text: String) -> AnyPublisher<Void, MessageError> {
        let publisher = dataSource.sendMessage(dogId: dog.id, text: text)
            .mapError { _ in MessageError.unavailable }
            .share()

        publisher.pss_sink { _ in }.store(in: &cancellables)

        return publisher.eraseToAnyPublisher()
    }
}
