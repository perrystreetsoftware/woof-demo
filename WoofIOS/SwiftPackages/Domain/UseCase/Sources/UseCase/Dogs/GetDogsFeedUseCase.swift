import Combine
import DI
import Models
import Repositories

@Factory
public final class GetDogsFeedUseCase {
    private let dogsRepository: DogsRepository
    private let moderationRepository: ModerationRepository

    public func callAsFunction() -> AnyPublisher<DogsFeed, Never> {
        Publishers.CombineLatest(
            dogsRepository.dogsFeed,
            moderationRepository.blockedDogIds
        )
        .map { feed, blockedIds in
            DogsFeed(
                dogs: feed.dogs.filter { !blockedIds.contains($0.id) },
                total: feed.total - blockedIds.count
            )
        }
        .removeDuplicates()
        .eraseToAnyPublisher()
    }
}
