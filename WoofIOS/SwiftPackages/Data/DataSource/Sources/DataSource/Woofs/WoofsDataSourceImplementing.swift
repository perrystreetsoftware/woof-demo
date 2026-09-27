import Combine
import DTO

public protocol WoofsDataSourceImplementing {
    func sendWoof(dogId: Int) -> AnyPublisher<Void, DataSourceError>
    func getReceivedWoofs() -> AnyPublisher<[ReceivedWoofDTO], DataSourceError>
}
