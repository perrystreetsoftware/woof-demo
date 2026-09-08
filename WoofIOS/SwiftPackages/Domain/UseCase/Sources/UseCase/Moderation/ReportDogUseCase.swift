import Combine
import DI
import Models
import Repositories

@Factory
public final class ReportDogUseCase {
    private let moderationRepository: ModerationRepository

    public func callAsFunction(dog: Dog) -> AnyPublisher<Void, ModerationError> {
        moderationRepository.addReport(dog: dog)
    }
}
