import Combine
import DI
import Models
import Repositories

@Factory
public final class ToggleDogFavoriteUseCase {
    private let favoritesRepository: FavoritesRepository

    public func callAsFunction(dog: Dog) -> AnyPublisher<Void, FavoritesError> {
        favoritesRepository.favoriteDogIds
            .first()
            .setFailureType(to: FavoritesError.self)
            .flatMap { [favoritesRepository] favoriteIds -> AnyPublisher<Void, FavoritesError> in
                switch favoriteIds.contains(dog.id) {
                case true: favoritesRepository.removeFavorite(dog: dog)
                case false: favoritesRepository.addFavorite(dog: dog)
                }
            }
            .eraseToAnyPublisher()
    }
}
