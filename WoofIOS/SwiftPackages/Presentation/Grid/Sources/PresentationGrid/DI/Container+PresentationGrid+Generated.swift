import Swinject
import SwinjectAutoregistration

public extension Container {
    func injectPresentationGridGenerated() -> Container {
        self.autoregister(DogDomainToCellUIModelMapper.self, initializer: DogDomainToCellUIModelMapper.init).inObjectScope(.transient)
        self.autoregister(GridViewModel.self, initializer: GridViewModel.init).inObjectScope(.transient)
        return self
    }
}
