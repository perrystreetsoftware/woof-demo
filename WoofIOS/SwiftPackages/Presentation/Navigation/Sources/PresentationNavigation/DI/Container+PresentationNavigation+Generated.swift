import Swinject
import SwinjectAutoregistration

public extension Container {
    func injectPresentationNavigationGenerated() -> Container {
        self.autoregister(NavigatorImplementing.self, initializer: WoofNavigator.init).inObjectScope(.container)
        self.autoregister(WoofBackStack.self, initializer: WoofBackStack.init).inObjectScope(.container)
        return self
    }
}
