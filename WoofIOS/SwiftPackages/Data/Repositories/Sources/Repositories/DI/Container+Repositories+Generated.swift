import Swinject
import SwinjectAutoregistration

public extension Container {
    func injectRepositoriesGenerated() -> Container {
        self.autoregister(AccountRepository.self, initializer: AccountRepository.init).inObjectScope(.container)
        self.autoregister(DogDTOToDomainMapper.self, initializer: DogDTOToDomainMapper.init).inObjectScope(.transient)
        self.autoregister(DogProfileDTOToDomainMapper.self, initializer: DogProfileDTOToDomainMapper.init).inObjectScope(.transient)
        self.autoregister(DogSizeDTOToDomainMapper.self, initializer: DogSizeDTOToDomainMapper.init).inObjectScope(.transient)
        self.autoregister(DogsPageDTOToDomainMapper.self, initializer: DogsPageDTOToDomainMapper.init).inObjectScope(.transient)
        self.autoregister(DogsRepository.self, initializer: DogsRepository.init).inObjectScope(.container)
        self.autoregister(FavoritesRepository.self, initializer: FavoritesRepository.init).inObjectScope(.container)
        self.autoregister(MessagesRepository.self, initializer: MessagesRepository.init).inObjectScope(.container)
        self.autoregister(ModerationRepository.self, initializer: ModerationRepository.init).inObjectScope(.container)
        self.autoregister(WoofsRepository.self, initializer: WoofsRepository.init).inObjectScope(.container)
        return self
    }
}
