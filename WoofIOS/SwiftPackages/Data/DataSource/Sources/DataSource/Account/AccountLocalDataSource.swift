import Combine
import DI
import DTO
import Foundation
import Utils

@SingleForProtocol
public final class AccountLocalDataSource: AccountDataSourceImplementing {
    private let scheduler: SchedulerProviding

    private static let latencyMillis = 600

    public func getAccount() -> AnyPublisher<DogProfileDTO, DataSourceError> {
        Just(DogFixtures.account())
            .setFailureType(to: DataSourceError.self)
            .delay(for: .milliseconds(Self.latencyMillis), scheduler: scheduler.computationScheduler)
            .receive(on: scheduler.mainScheduler)
            .eraseToAnyPublisher()
    }
}
