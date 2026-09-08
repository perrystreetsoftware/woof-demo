import Combine
import DI
import Models
import Repositories

@Factory
public final class GetDogProfileUseCase {
    private let dogsRepository: DogsRepository

    public func callAsFunction(dog: Dog) -> AnyPublisher<DogProfile, DogsError> {
        dogsRepository.getDogProfile(dog: dog)
    }
}
