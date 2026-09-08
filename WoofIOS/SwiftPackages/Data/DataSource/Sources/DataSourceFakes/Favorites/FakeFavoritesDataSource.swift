import Combine
import DI
import DataSource
import Foundation
import Utils

@MockApi
public final class FakeFavoritesDataSource: FavoritesDataSourceImplementing {
    public private(set) var favoriteDogIds = Set<Int>()
    public var addFavoriteError: DataSourceError?

    private let scheduler: SchedulerProviding

    public func addFavorite(dogId: Int) -> AnyPublisher<Void, DataSourceError> {
        Deferred { [weak self] () -> AnyPublisher<Void, DataSourceError> in
            guard let self else { return Empty().eraseToAnyPublisher() }
            if let addFavoriteError {
                return Fail(error: addFavoriteError).eraseToAnyPublisher()
            }
            favoriteDogIds.insert(dogId)
            return Just(()).setFailureType(to: DataSourceError.self).eraseToAnyPublisher()
        }
        .receive(on: scheduler.mainScheduler)
        .eraseToAnyPublisher()
    }

    public func removeFavorite(dogId: Int) -> AnyPublisher<Void, DataSourceError> {
        Deferred { [weak self] () -> AnyPublisher<Void, DataSourceError> in
            guard let self else { return Empty().eraseToAnyPublisher() }
            favoriteDogIds.remove(dogId)
            return Just(()).setFailureType(to: DataSourceError.self).eraseToAnyPublisher()
        }
        .receive(on: scheduler.mainScheduler)
        .eraseToAnyPublisher()
    }
}
