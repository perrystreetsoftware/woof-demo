import Swinject
import SwinjectAutoregistration

public extension Container {
    func injectPresentationFavoritesGenerated() -> Container {
        self.autoregister(DogDomainToFavoriteCellUIModelMapper.self, initializer: DogDomainToFavoriteCellUIModelMapper.init).inObjectScope(.transient)
        self.autoregister(FavoritesViewModel.self, initializer: FavoritesViewModel.init).inObjectScope(.transient)
        return self
    }
}
