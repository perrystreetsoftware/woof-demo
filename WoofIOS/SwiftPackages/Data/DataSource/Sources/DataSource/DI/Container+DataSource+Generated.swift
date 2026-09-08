import Swinject
import SwinjectAutoregistration

public extension Container {
    func injectDataSourceGenerated() -> Container {
        self.autoregister(AccountDataSourceImplementing.self, initializer: AccountLocalDataSource.init).inObjectScope(.container)
        self.autoregister(DogsDataSourceImplementing.self, initializer: DogsLocalDataSource.init).inObjectScope(.container)
        self.autoregister(FavoritesDataSourceImplementing.self, initializer: FavoritesLocalDataSource.init).inObjectScope(.container)
        self.autoregister(MessagesDataSourceImplementing.self, initializer: MessagesLocalDataSource.init).inObjectScope(.container)
        self.autoregister(ModerationDataSourceImplementing.self, initializer: ModerationLocalDataSource.init).inObjectScope(.container)
        self.autoregister(WoofsDataSourceImplementing.self, initializer: WoofsLocalDataSource.init).inObjectScope(.container)
        return self
    }
}
