import Combine
import DI
import DTO
import Foundation
import Utils

@SingleForProtocol
public final class WoofsLocalDataSource: WoofsDataSourceImplementing {
    private let scheduler: SchedulerProviding

    private var woofedDogIds = WoofsLocalDataSource.woofedBackDogIds

    private static let receivedWoofs: [(dogId: Int, minutesAgo: Int64)] = [(1, 5), (2, 120), (3, 1_440), (4, 4_320)]
    private static let woofedBackDogIds: Set<Int> = [2, 4]

    public func sendWoof(dogId: Int) -> AnyPublisher<Void, DataSourceError> {
        Deferred { [weak self] in
            Just(self?.woofedDogIds.insert(dogId)).map { _ in () }.setFailureType(to: DataSourceError.self)
        }
        .receive(on: scheduler.mainScheduler)
        .eraseToAnyPublisher()
    }

    public func getReceivedWoofs() -> AnyPublisher<[ReceivedWoofDTO], DataSourceError> {
        Deferred { [weak self] () -> AnyPublisher<[ReceivedWoofDTO], DataSourceError> in
            let woofedDogIds = self?.woofedDogIds ?? []
            let now = Int64(Date().timeIntervalSince1970 * 1_000)
            let woofs = Self.receivedWoofs.compactMap { woof -> ReceivedWoofDTO? in
                guard let profile = DogFixtures.profile(dogId: woof.dogId) else { return nil }
                return ReceivedWoofDTO(
                    dog: profile.dog,
                    woofedAtMillis: now - woof.minutesAgo * 60_000,
                    woofedBack: woofedDogIds.contains(woof.dogId)
                )
            }
            return Just(woofs).setFailureType(to: DataSourceError.self).eraseToAnyPublisher()
        }
        .receive(on: scheduler.mainScheduler)
        .eraseToAnyPublisher()
    }
}
