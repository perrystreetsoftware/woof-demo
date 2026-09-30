import Swinject
import SwinjectAutoregistration

public extension Container {
    func injectPresentationBrowseGenerated() -> Container {
        self.autoregister(DogDomainToCellUIModelMapper.self, initializer: DogDomainToCellUIModelMapper.init).inObjectScope(.transient)
        self.autoregister(BrowseViewModel.self, initializer: BrowseViewModel.init).inObjectScope(.transient)
        return self
    }
}
