import DataSource
import Swinject
import SwinjectAutoregistration

public final class ModerationDataSourceFactory {
    private let dataSource: FakeModerationDataSource

    public init(_ container: Container) {
        dataSource = container~>
    }

    @discardableResult
    public func withBlockDogError() -> Self {
        dataSource.blockDogError = .unavailable("Could not block dog")
        return self
    }
}
