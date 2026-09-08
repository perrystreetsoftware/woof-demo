import Combine
import DI
import DataSource
import Models

@Single
public final class DogsRepository {
    private let dataSource: DogsDataSourceImplementing
    private let pageMapper: DogsPageDTOToDomainMapper
    private let profileMapper: DogProfileDTOToDomainMapper

    private let feed = CurrentValueSubject<DogsFeed, Never>(.empty)
    private let profiles = CurrentValueSubject<[Int: DogProfile], Never>([:])

    public var dogsFeed: AnyPublisher<DogsFeed, Never> {
        feed.eraseToAnyPublisher()
    }

    public func getDogs(offset: Int, limit: Int) -> AnyPublisher<DogsPage, DogsError> {
        dataSource.getDogs(offset: offset, limit: limit)
            .map { [pageMapper] in pageMapper($0) }
            .mapError { _ in DogsError.unavailable }
            .handleEvents(receiveOutput: { [feed] page in feed.send(feed.value.append(page)) })
            .eraseToAnyPublisher()
    }

    public func getDogProfile(dog: Dog) -> AnyPublisher<DogProfile, DogsError> {
        if let profile = profiles.value[dog.id] {
            return Just(profile).setFailureType(to: DogsError.self).eraseToAnyPublisher()
        }
        return dataSource.getDogProfile(dogId: dog.id)
            .map { [profileMapper] in profileMapper($0) }
            .mapError { _ in DogsError.unavailable }
            .handleEvents(receiveOutput: { [profiles] profile in
                var cached = profiles.value
                cached[dog.id] = profile
                profiles.send(cached)
            })
            .eraseToAnyPublisher()
    }
}

private extension DogsFeed {
    func append(_ page: DogsPage) -> DogsFeed {
        let knownIds = Set(dogs.map(\.id))
        let newDogs = page.dogs.filter { !knownIds.contains($0.id) }
        return DogsFeed(dogs: dogs + newDogs, total: page.total)
    }
}
