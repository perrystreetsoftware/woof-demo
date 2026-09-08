import Combine
import DI
import Foundation
import Utils

@SingleForProtocol
public final class WoofsLocalDataSource: WoofsDataSourceImplementing {
    private let scheduler: SchedulerProviding

    private var woofedDogIds = Set<Int>()

    public func sendWoof(dogId: Int) -> AnyPublisher<Void, DataSourceError> {
        Deferred { [weak self] in
            Just(self?.woofedDogIds.insert(dogId)).map { _ in () }.setFailureType(to: DataSourceError.self)
        }
        .receive(on: scheduler.mainScheduler)
        .eraseToAnyPublisher()
    }
}
