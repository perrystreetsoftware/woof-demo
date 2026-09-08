import Combine
import DI
import Models
import Repositories

@Factory
public final class SendWoofUseCase {
    private let woofsRepository: WoofsRepository

    public func callAsFunction(dog: Dog) -> AnyPublisher<Void, WoofError> {
        woofsRepository.woofedDogIds
            .first()
            .setFailureType(to: WoofError.self)
            .flatMap { [woofsRepository] woofedIds -> AnyPublisher<Void, WoofError> in
                switch woofedIds.contains(dog.id) {
                case true: Fail(error: WoofError.alreadyWoofed).eraseToAnyPublisher()
                case false: woofsRepository.addWoof(dog: dog)
                }
            }
            .eraseToAnyPublisher()
    }
}
