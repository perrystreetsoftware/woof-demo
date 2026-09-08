import Combine

public protocol WoofsDataSourceImplementing {
    func sendWoof(dogId: Int) -> AnyPublisher<Void, DataSourceError>
}
