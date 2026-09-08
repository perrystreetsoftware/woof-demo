import Combine

public protocol FavoritesDataSourceImplementing {
    func addFavorite(dogId: Int) -> AnyPublisher<Void, DataSourceError>
    func removeFavorite(dogId: Int) -> AnyPublisher<Void, DataSourceError>
}
