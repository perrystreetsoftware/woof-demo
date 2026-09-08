import DataSource
import Swinject
import SwinjectAutoregistration
import Utils

public extension Container {
    func injectDataSourceFakesGenerated() -> Container {
        self.pss_registerMock(AccountDataSourceImplementing.self, FakeAccountDataSource.self, FakeAccountDataSource.init)
        self.pss_registerMock(DogsDataSourceImplementing.self, FakeDogsDataSource.self, FakeDogsDataSource.init)
        self.pss_registerMock(FavoritesDataSourceImplementing.self, FakeFavoritesDataSource.self, FakeFavoritesDataSource.init)
        self.pss_registerMock(MessagesDataSourceImplementing.self, FakeMessagesDataSource.self, FakeMessagesDataSource.init)
        self.pss_registerMock(ModerationDataSourceImplementing.self, FakeModerationDataSource.self, FakeModerationDataSource.init)
        self.pss_registerMock(WoofsDataSourceImplementing.self, FakeWoofsDataSource.self, FakeWoofsDataSource.init)
        return self
    }
}
