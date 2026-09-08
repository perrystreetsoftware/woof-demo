import Combine

public protocol ModerationDataSourceImplementing {
    func reportDog(dogId: Int) -> AnyPublisher<Void, DataSourceError>
    func blockDog(dogId: Int) -> AnyPublisher<Void, DataSourceError>
}
