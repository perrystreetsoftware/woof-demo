import Combine
import DI
import DataSource
import Foundation
import Utils

public struct SentMessage: Equatable {
    public let dogId: Int
    public let text: String

    public init(dogId: Int, text: String) {
        self.dogId = dogId
        self.text = text
    }
}

@MockApi
public final class FakeMessagesDataSource: MessagesDataSourceImplementing {
    public private(set) var sentMessages: [SentMessage] = []
    public var sendMessageError: DataSourceError?

    private let scheduler: SchedulerProviding

    public func sendMessage(dogId: Int, text: String) -> AnyPublisher<Void, DataSourceError> {
        Deferred { [weak self] () -> AnyPublisher<Void, DataSourceError> in
            guard let self else { return Empty().eraseToAnyPublisher() }
            if let sendMessageError {
                return Fail(error: sendMessageError).eraseToAnyPublisher()
            }
            sentMessages.append(SentMessage(dogId: dogId, text: text))
            return Just(()).setFailureType(to: DataSourceError.self).eraseToAnyPublisher()
        }
        .receive(on: scheduler.mainScheduler)
        .eraseToAnyPublisher()
    }
}
