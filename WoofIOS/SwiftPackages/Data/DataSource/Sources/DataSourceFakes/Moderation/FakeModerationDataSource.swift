import Combine
import DI
import DataSource
import Foundation
import Utils

@MockApi
public final class FakeModerationDataSource: ModerationDataSourceImplementing {
    public private(set) var reportedDogIds: [Int] = []
    public private(set) var blockedDogIds: [Int] = []
    public var blockDogError: DataSourceError?

    private let scheduler: SchedulerProviding

    public func reportDog(dogId: Int) -> AnyPublisher<Void, DataSourceError> {
        Deferred { [weak self] () -> AnyPublisher<Void, DataSourceError> in
            guard let self else { return Empty().eraseToAnyPublisher() }
            reportedDogIds.append(dogId)
            return Just(()).setFailureType(to: DataSourceError.self).eraseToAnyPublisher()
        }
        .receive(on: scheduler.mainScheduler)
        .eraseToAnyPublisher()
    }

    public func blockDog(dogId: Int) -> AnyPublisher<Void, DataSourceError> {
        Deferred { [weak self] () -> AnyPublisher<Void, DataSourceError> in
            guard let self else { return Empty().eraseToAnyPublisher() }
            if let blockDogError {
                return Fail(error: blockDogError).eraseToAnyPublisher()
            }
            blockedDogIds.append(dogId)
            return Just(()).setFailureType(to: DataSourceError.self).eraseToAnyPublisher()
        }
        .receive(on: scheduler.mainScheduler)
        .eraseToAnyPublisher()
    }
}
