import DataSourceFakes
import Repositories
import Swinject
import SwinjectAutoregistration
import UseCase
import Utils

public extension Container {
    func injectCoreForViewModelTests() -> Container {
        self.register(TestSchedulerProvider.self) { _ in TestSchedulerProvider() }
            .implements(SchedulerProviding.self)
            .inObjectScope(.container)
        return
            self
            .injectDataSourceFakesGenerated()
            .injectRepositoriesGenerated()
            .injectUseCaseGenerated()
    }
}
