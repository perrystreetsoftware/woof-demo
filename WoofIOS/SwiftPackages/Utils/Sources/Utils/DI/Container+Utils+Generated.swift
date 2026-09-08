import Swinject
import SwinjectAutoregistration

public extension Container {
    func injectUtilsGenerated() -> Container {
        self.autoregister(SchedulerProviding.self, initializer: SchedulerProvider.init).inObjectScope(.container)
        return self
    }
}
