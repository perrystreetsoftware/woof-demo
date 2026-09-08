import DI
import Models
import PresentationCommon
import PresentationNavigation
import Swinject
import SwinjectAutoregistration
import UseCase
import Utils

public extension Container {
    func injectPresentationProfileGenerated() -> Container {
        self.autoregister(DogDomainToProfilePageUIModelMapper.self, initializer: DogDomainToProfilePageUIModelMapper.init).inObjectScope(.transient)
        self.autoregister(DogProfileDomainToUIModelMapper.self, initializer: DogProfileDomainToUIModelMapper.init).inObjectScope(.transient)
        self.autoregister(ProfileDetailsViewModel.self, argument: Dog.self, initializer: ProfileDetailsViewModel.init).inObjectScope(.transient)
        self.autoregister(ProfileHeaderViewModel.self, argument: Dog.self, initializer: ProfileHeaderViewModel.init).inObjectScope(.transient)
        self.autoregister(ProfileMessageViewModel.self, argument: Dog.self, initializer: ProfileMessageViewModel.init).inObjectScope(.transient)
        self.autoregister(ProfileModerationViewModel.self, argument: Dog.self, initializer: ProfileModerationViewModel.init).inObjectScope(.transient)
        self.autoregister(ProfilePagerViewModel.self, argument: Int.self, initializer: ProfilePagerViewModel.init).inObjectScope(.transient)
        self.autoregister(ProfileRouterEntry.self, initializer: ProfileRouterEntry.init).inObjectScope(.transient)
        self.autoregister(ProfileWoofViewModel.self, argument: Dog.self, initializer: ProfileWoofViewModel.init).inObjectScope(.transient)
        self.register([RouterEntryImplementing].self) { r in
            return [
                r.resolve(ProfileRouterEntry.self)!
            ]
        }
        return self
    }
}
