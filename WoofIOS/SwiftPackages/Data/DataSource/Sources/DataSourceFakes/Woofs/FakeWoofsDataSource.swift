import Combine
import DI
import DataSource
import Foundation
import Utils

@MockApi
public final class FakeWoofsDataSource: WoofsDataSourceImplementing {
    public private(set) var woofedDogIds: [Int] = []
    public var sendWoofError: DataSourceError?

    private let scheduler: SchedulerProviding

    public func sendWoof(dogId: Int) -> AnyPublisher<Void, DataSourceError> {
        Deferred { [weak self] () -> AnyPublisher<Void, DataSourceError> in
            guard let self else { return Empty().eraseToAnyPublisher() }
            if let sendWoofError {
                return Fail(error: sendWoofError).eraseToAnyPublisher()
            }
            woofedDogIds.append(dogId)
            return Just(()).setFailureType(to: DataSourceError.self).eraseToAnyPublisher()
        }
        .receive(on: scheduler.mainScheduler)
        .eraseToAnyPublisher()
    }
}
