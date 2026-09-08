import Combine
import DI
import Foundation
import Utils

@SingleForProtocol
public final class MessagesLocalDataSource: MessagesDataSourceImplementing {
    private let scheduler: SchedulerProviding

    private var sentMessages: [Int: [String]] = [:]

    public func sendMessage(dogId: Int, text: String) -> AnyPublisher<Void, DataSourceError> {
        Deferred { [weak self] in
            Just(self?.sentMessages[dogId, default: []].append(text)).map { _ in () }.setFailureType(to: DataSourceError.self)
        }
        .receive(on: scheduler.mainScheduler)
        .eraseToAnyPublisher()
    }
}
