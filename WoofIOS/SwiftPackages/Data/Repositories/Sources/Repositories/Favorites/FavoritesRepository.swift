import Combine
import DI
import DataSource
import Models
import Utils

@Single
public final class FavoritesRepository {
    private let dataSource: FavoritesDataSourceImplementing

    private let favorites = CurrentValueSubject<Set<Int>, Never>([])
    private var cancellables = Set<AnyCancellable>()

    public var favoriteDogIds: AnyPublisher<Set<Int>, Never> {
        favorites.eraseToAnyPublisher()
    }

    public func addFavorite(dog: Dog) -> AnyPublisher<Void, FavoritesError> {
        let publisher = dataSource.addFavorite(dogId: dog.id)
            .mapError { _ in FavoritesError.unavailable }
            .handleEvents(
                receiveSubscription: { [favorites] _ in
                    favorites.send(favorites.value.union([dog.id]))
                },
                receiveCompletion: { [favorites] completion in
                    if case .failure = completion {
                        favorites.send(favorites.value.subtracting([dog.id]))
                    }
                }
            )
            .share()

        publisher.pss_sink { _ in }.store(in: &cancellables)

        return publisher.eraseToAnyPublisher()
    }

    public func removeFavorite(dog: Dog) -> AnyPublisher<Void, FavoritesError> {
        let publisher = dataSource.removeFavorite(dogId: dog.id)
            .mapError { _ in FavoritesError.unavailable }
            .handleEvents(
                receiveSubscription: { [favorites] _ in
                    favorites.send(favorites.value.subtracting([dog.id]))
                },
                receiveCompletion: { [favorites] completion in
                    if case .failure = completion {
                        favorites.send(favorites.value.union([dog.id]))
                    }
                }
            )
            .share()

        publisher.pss_sink { _ in }.store(in: &cancellables)

        return publisher.eraseToAnyPublisher()
    }
}
