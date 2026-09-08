import Combine
import DI
import Models
import Repositories

@Factory
public final class LoadNextDogsPageUseCase {
    public static let pageSize = 200

    private let dogsRepository: DogsRepository

    public func callAsFunction() -> AnyPublisher<DogsPage, DogsError> {
        dogsRepository.dogsFeed
            .first()
            .setFailureType(to: DogsError.self)
            .flatMap { [dogsRepository] feed in
                dogsRepository.getDogs(offset: feed.dogs.count, limit: Self.pageSize)
            }
            .eraseToAnyPublisher()
    }
}
