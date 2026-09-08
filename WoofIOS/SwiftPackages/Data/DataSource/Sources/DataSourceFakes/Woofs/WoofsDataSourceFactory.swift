import DataSource
import Swinject
import SwinjectAutoregistration

public final class WoofsDataSourceFactory {
    private let dataSource: FakeWoofsDataSource

    public init(_ container: Container) {
        dataSource = container~>
    }

    @discardableResult
    public func withSendWoofError() -> Self {
        dataSource.sendWoofError = .unavailable("Could not send woof")
        return self
    }
}
