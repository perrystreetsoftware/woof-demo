import Combine
import DI
import DTO
import DataSource
import Foundation
import Utils

@MockApi
public final class FakeWoofsDataSource: WoofsDataSourceImplementing {
    public private(set) var woofedDogIds: [Int] = []
    public var receivedWoofs: [ReceivedWoofDTO] = []
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

    public func getReceivedWoofs() -> AnyPublisher<[ReceivedWoofDTO], DataSourceError> {
        Just(receivedWoofs)
            .setFailureType(to: DataSourceError.self)
            .receive(on: scheduler.mainScheduler)
            .eraseToAnyPublisher()
    }
}
