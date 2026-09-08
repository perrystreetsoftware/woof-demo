import Combine
import DI
import DataSource
import Models
import Utils

@Single
public final class ModerationRepository {
    private let dataSource: ModerationDataSourceImplementing

    private let blocks = CurrentValueSubject<Set<Int>, Never>([])
    private var cancellables = Set<AnyCancellable>()

    public var blockedDogIds: AnyPublisher<Set<Int>, Never> {
        blocks.eraseToAnyPublisher()
    }

    public func addReport(dog: Dog) -> AnyPublisher<Void, ModerationError> {
        let publisher = dataSource.reportDog(dogId: dog.id)
            .mapError { _ in ModerationError.unavailable }
            .share()

        publisher.pss_sink { _ in }.store(in: &cancellables)

        return publisher.eraseToAnyPublisher()
    }

    public func addBlock(dog: Dog) -> AnyPublisher<Void, ModerationError> {
        let publisher = dataSource.blockDog(dogId: dog.id)
            .mapError { _ in ModerationError.unavailable }
            .handleEvents(receiveOutput: { [blocks] in blocks.send(blocks.value.union([dog.id])) })
            .share()

        publisher.pss_sink { _ in }.store(in: &cancellables)

        return publisher.eraseToAnyPublisher()
    }
}
