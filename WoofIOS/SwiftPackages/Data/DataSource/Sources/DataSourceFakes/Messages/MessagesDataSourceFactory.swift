import DataSource
import Swinject
import SwinjectAutoregistration

public final class MessagesDataSourceFactory {
    private let dataSource: FakeMessagesDataSource

    public init(_ container: Container) {
        dataSource = container~>
    }

    @discardableResult
    public func withSendMessageError() -> Self {
        dataSource.sendMessageError = .unavailable("Could not send message")
        return self
    }
}
