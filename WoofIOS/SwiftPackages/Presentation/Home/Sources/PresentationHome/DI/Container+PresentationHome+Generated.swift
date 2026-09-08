import PresentationNavigation
import Swinject
import SwinjectAutoregistration

public extension Container {
    func injectPresentationHomeGenerated() -> Container {
        self.autoregister(HomeRouterEntry.self, initializer: HomeRouterEntry.init).inObjectScope(.transient)
        self.autoregister(HomeViewModel.self, initializer: HomeViewModel.init).inObjectScope(.transient)
        self.register([RouterEntryImplementing].self) { r in
            return [
                r.resolve(HomeRouterEntry.self)!
            ]
        }
        return self
    }
}
