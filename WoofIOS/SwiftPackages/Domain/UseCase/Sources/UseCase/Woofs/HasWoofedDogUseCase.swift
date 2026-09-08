import Combine
import DI
import Models
import Repositories

@Factory
public final class HasWoofedDogUseCase {
    private let woofsRepository: WoofsRepository

    public func callAsFunction(dog: Dog) -> AnyPublisher<Bool, Never> {
        woofsRepository.woofedDogIds
            .map { $0.contains(dog.id) }
            .removeDuplicates()
            .eraseToAnyPublisher()
    }
}
