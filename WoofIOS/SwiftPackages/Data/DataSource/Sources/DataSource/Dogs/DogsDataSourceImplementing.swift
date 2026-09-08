import Combine
import DTO

public protocol DogsDataSourceImplementing {
    func getDogs(offset: Int, limit: Int) -> AnyPublisher<DogsPageDTO, DataSourceError>
    func getDogProfile(dogId: Int) -> AnyPublisher<DogProfileDTO, DataSourceError>
}
