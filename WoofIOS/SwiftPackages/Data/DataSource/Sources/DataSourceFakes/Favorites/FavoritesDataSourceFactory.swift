import DataSource
import Swinject
import SwinjectAutoregistration

public final class FavoritesDataSourceFactory {
    private let dataSource: FakeFavoritesDataSource

    public init(_ container: Container) {
        dataSource = container~>
    }

    @discardableResult
    public func withAddFavoriteError() -> Self {
        dataSource.addFavoriteError = .unavailable("Could not add favorite")
        return self
    }
}
