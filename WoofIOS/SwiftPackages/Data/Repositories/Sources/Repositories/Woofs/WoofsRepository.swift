import Combine
import DI
import DataSource
import Models
import Utils

@Single
public final class WoofsRepository {
    private let dataSource: WoofsDataSourceImplementing

    private let woofs = CurrentValueSubject<Set<Int>, Never>([])
    private var cancellables = Set<AnyCancellable>()

    public var woofedDogIds: AnyPublisher<Set<Int>, Never> {
        woofs.eraseToAnyPublisher()
    }

    public func addWoof(dog: Dog) -> AnyPublisher<Void, WoofError> {
        let publisher = dataSource.sendWoof(dogId: dog.id)
            .mapError { _ in WoofError.unavailable }
            .handleEvents(receiveOutput: { [woofs] in woofs.send(woofs.value.union([dog.id])) })
            .share()

        publisher.pss_sink { _ in }.store(in: &cancellables)

        return publisher.eraseToAnyPublisher()
    }
}
