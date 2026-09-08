import Combine
import DI
import Models
import Repositories

@Factory
public final class IsDogFavoriteUseCase {
    private let favoritesRepository: FavoritesRepository

    public func callAsFunction(dog: Dog) -> AnyPublisher<Bool, Never> {
        favoritesRepository.favoriteDogIds
            .map { $0.contains(dog.id) }
            .removeDuplicates()
            .eraseToAnyPublisher()
    }
}
