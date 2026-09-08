import Swinject
import SwinjectAutoregistration

public extension Container {
    func injectPresentationAccountGenerated() -> Container {
        self.autoregister(AccountViewModel.self, initializer: AccountViewModel.init).inObjectScope(.transient)
        self.autoregister(DogProfileDomainToAccountUIModelMapper.self, initializer: DogProfileDomainToAccountUIModelMapper.init).inObjectScope(.transient)
        return self
    }
}
