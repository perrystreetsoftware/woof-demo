import Combine
import DI
import DTO
import Foundation
import Utils

@SingleForProtocol
public final class DogsLocalDataSource: DogsDataSourceImplementing {
    private let scheduler: SchedulerProviding

    private static let gridLatencyMillis = 1200
    private static let profileLatencyMillis = 900

    public func getDogs(offset: Int, limit: Int) -> AnyPublisher<DogsPageDTO, DataSourceError> {
        Just(DogFixtures.page(offset: offset, limit: limit))
            .setFailureType(to: DataSourceError.self)
            .simulateNetworkLatency(millis: Self.gridLatencyMillis, scheduler: scheduler)
    }

    public func getDogProfile(dogId: Int) -> AnyPublisher<DogProfileDTO, DataSourceError> {
        Deferred { () -> AnyPublisher<DogProfileDTO, DataSourceError> in
            guard let profile = DogFixtures.profile(dogId: dogId) else {
                return Fail(error: DataSourceError.notFound(dogId: dogId)).eraseToAnyPublisher()
            }
            return Just(profile).setFailureType(to: DataSourceError.self).eraseToAnyPublisher()
        }
        .simulateNetworkLatency(millis: Self.profileLatencyMillis, scheduler: scheduler)
    }
}

private extension Publisher where Failure == DataSourceError {
    func simulateNetworkLatency(millis: Int, scheduler: SchedulerProviding) -> AnyPublisher<Output, DataSourceError> {
        delay(for: .milliseconds(millis), scheduler: scheduler.computationScheduler)
            .receive(on: scheduler.mainScheduler)
            .eraseToAnyPublisher()
    }
}
