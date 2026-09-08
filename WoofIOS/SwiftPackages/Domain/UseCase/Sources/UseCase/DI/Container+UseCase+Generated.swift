import Swinject
import SwinjectAutoregistration

public extension Container {
    func injectUseCaseGenerated() -> Container {
        self.autoregister(BlockDogUseCase.self, initializer: BlockDogUseCase.init).inObjectScope(.transient)
        self.autoregister(GetAccountUseCase.self, initializer: GetAccountUseCase.init).inObjectScope(.transient)
        self.autoregister(GetDogProfileUseCase.self, initializer: GetDogProfileUseCase.init).inObjectScope(.transient)
        self.autoregister(GetDogsFeedUseCase.self, initializer: GetDogsFeedUseCase.init).inObjectScope(.transient)
        self.autoregister(GetFavoriteDogsUseCase.self, initializer: GetFavoriteDogsUseCase.init).inObjectScope(.transient)
        self.autoregister(HasWoofedDogUseCase.self, initializer: HasWoofedDogUseCase.init).inObjectScope(.transient)
        self.autoregister(IsDogFavoriteUseCase.self, initializer: IsDogFavoriteUseCase.init).inObjectScope(.transient)
        self.autoregister(LoadNextDogsPageUseCase.self, initializer: LoadNextDogsPageUseCase.init).inObjectScope(.transient)
        self.autoregister(ReportDogUseCase.self, initializer: ReportDogUseCase.init).inObjectScope(.transient)
        self.autoregister(SendMessageUseCase.self, initializer: SendMessageUseCase.init).inObjectScope(.transient)
        self.autoregister(SendWoofUseCase.self, initializer: SendWoofUseCase.init).inObjectScope(.transient)
        self.autoregister(ToggleDogFavoriteUseCase.self, initializer: ToggleDogFavoriteUseCase.init).inObjectScope(.transient)
        return self
    }
}
