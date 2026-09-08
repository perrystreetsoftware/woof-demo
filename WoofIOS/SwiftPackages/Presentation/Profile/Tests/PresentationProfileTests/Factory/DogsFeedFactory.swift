import Combine
import DataSourceFakes
import Repositories
import Swinject
import SwinjectAutoregistration
import TestUtils
import Utils

final class DogsFeedFactory {
    private let container: Container
    private let repository: DogsRepository
    private var cancellables = Set<AnyCancellable>()

    init(_ container: Container) {
        self.container = container
        repository = container~>
    }

    @discardableResult
    func withLoadedDogs(count: Int) -> Self {
        DogsDataSourceFactory(container).withDogs(count: count)
        repository.getDogs(offset: 0, limit: count).pss_sink { _ in }.store(in: &cancellables)
        TimeAdvancingFactory(container).tick()
        return self
    }
}
