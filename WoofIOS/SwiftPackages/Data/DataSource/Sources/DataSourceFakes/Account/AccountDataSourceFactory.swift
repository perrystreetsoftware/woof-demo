import DataSource
import Swinject
import SwinjectAutoregistration

public final class AccountDataSourceFactory {
    private let dataSource: FakeAccountDataSource

    public init(_ container: Container) {
        dataSource = container~>
    }

    @discardableResult
    public func withAccountError() -> Self {
        dataSource.getAccountError = .unavailable("Could not load account")
        return self
    }
}
