import Combine

public protocol MessagesDataSourceImplementing {
    func sendMessage(dogId: Int, text: String) -> AnyPublisher<Void, DataSourceError>
}
