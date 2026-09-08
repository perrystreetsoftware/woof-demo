import Combine
import DI
import Models
import Repositories

@Factory
public final class GetFavoriteDogsUseCase {
    private let getDogsFeedUseCase: GetDogsFeedUseCase
    private let favoritesRepository: FavoritesRepository

    public func callAsFunction() -> AnyPublisher<[Dog], Never> {
        Publishers.CombineLatest(
            getDogsFeedUseCase(),
            favoritesRepository.favoriteDogIds
        )
        .map { feed, favoriteIds in
            feed.dogs.filter { favoriteIds.contains($0.id) }
        }
        .removeDuplicates()
        .eraseToAnyPublisher()
    }
}
