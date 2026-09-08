import Combine
import DI
import Foundation
import Utils

@SingleForProtocol
public final class ModerationLocalDataSource: ModerationDataSourceImplementing {
    private let scheduler: SchedulerProviding

    private var reportedDogIds = Set<Int>()
    private var blockedDogIds = Set<Int>()

    public func reportDog(dogId: Int) -> AnyPublisher<Void, DataSourceError> {
        Deferred { [weak self] in
            Just(self?.reportedDogIds.insert(dogId)).map { _ in () }.setFailureType(to: DataSourceError.self)
        }
        .receive(on: scheduler.mainScheduler)
        .eraseToAnyPublisher()
    }

    public func blockDog(dogId: Int) -> AnyPublisher<Void, DataSourceError> {
        Deferred { [weak self] in
            Just(self?.blockedDogIds.insert(dogId)).map { _ in () }.setFailureType(to: DataSourceError.self)
        }
        .receive(on: scheduler.mainScheduler)
        .eraseToAnyPublisher()
    }
}
