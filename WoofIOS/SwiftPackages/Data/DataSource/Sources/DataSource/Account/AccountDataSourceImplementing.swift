import Combine
import DTO

public protocol AccountDataSourceImplementing {
    func getAccount() -> AnyPublisher<DogProfileDTO, DataSourceError>
}
