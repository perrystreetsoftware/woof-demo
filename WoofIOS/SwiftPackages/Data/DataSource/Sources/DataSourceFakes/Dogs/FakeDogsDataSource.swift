import Combine
import DI
import DTO
import DataSource
import Foundation
import Utils

@MockApi
public final class FakeDogsDataSource: DogsDataSourceImplementing {
    public var dogs: [DogDTO] = []
    public var getDogsError: DataSourceError?
    public var getDogsCount = 0
    public var getDogProfileError: DataSourceError?
    public var getDogProfileCount = 0

    private let scheduler: SchedulerProviding

    public func getDogs(offset: Int, limit: Int) -> AnyPublisher<DogsPageDTO, DataSourceError> {
        Deferred { [weak self] () -> AnyPublisher<DogsPageDTO, DataSourceError> in
            guard let self else { return Empty().eraseToAnyPublisher() }
            getDogsCount += 1
            if let getDogsError {
                return Fail(error: getDogsError).eraseToAnyPublisher()
            }
            return Just(
                DogsPageDTO(results: Array(dogs.dropFirst(offset).prefix(limit)), offset: offset, total: dogs.count)
            )
            .setFailureType(to: DataSourceError.self)
            .eraseToAnyPublisher()
        }
        .receive(on: scheduler.mainScheduler)
        .eraseToAnyPublisher()
    }

    public func getDogProfile(dogId: Int) -> AnyPublisher<DogProfileDTO, DataSourceError> {
        Deferred { [weak self] () -> AnyPublisher<DogProfileDTO, DataSourceError> in
            guard let self else { return Empty().eraseToAnyPublisher() }
            getDogProfileCount += 1
            if let getDogProfileError {
                return Fail(error: getDogProfileError).eraseToAnyPublisher()
            }
            guard let dog = dogs.first(where: { $0.id == dogId }) else {
                return Fail(error: DataSourceError.notFound(dogId: dogId)).eraseToAnyPublisher()
            }
            return Just(profileOf(dog)).setFailureType(to: DataSourceError.self).eraseToAnyPublisher()
        }
        .receive(on: scheduler.mainScheduler)
        .eraseToAnyPublisher()
    }

    private func profileOf(_ dog: DogDTO) -> DogProfileDTO {
        DogProfileDTO(
            dog: dog,
            breed: "Golden Retriever",
            ageInYears: 4,
            size: DogSizeDTO.large,
            neighborhood: "Kolonaki, Athens",
            personality: ["Playful", "Cuddly"],
            favoriteActivity: "Swimming at the beach",
            bio: "Loves swimming, tennis balls, and stealing snacks."
        )
    }
}
