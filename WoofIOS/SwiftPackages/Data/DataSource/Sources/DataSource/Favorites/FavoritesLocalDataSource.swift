import Combine
import DI
import Foundation
import Utils

@SingleForProtocol
public final class FavoritesLocalDataSource: FavoritesDataSourceImplementing {
    private let scheduler: SchedulerProviding

    private var favoriteDogIds = Set<Int>()

    public func addFavorite(dogId: Int) -> AnyPublisher<Void, DataSourceError> {
        Deferred { [weak self] in
            Just(self?.favoriteDogIds.insert(dogId)).map { _ in () }.setFailureType(to: DataSourceError.self)
        }
        .receive(on: scheduler.mainScheduler)
        .eraseToAnyPublisher()
    }

    public func removeFavorite(dogId: Int) -> AnyPublisher<Void, DataSourceError> {
        Deferred { [weak self] in
            Just(self?.favoriteDogIds.remove(dogId)).map { _ in () }.setFailureType(to: DataSourceError.self)
        }
        .receive(on: scheduler.mainScheduler)
        .eraseToAnyPublisher()
    }
}
