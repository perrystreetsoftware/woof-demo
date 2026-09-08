import DTO
import DataSource
import Swinject
import SwinjectAutoregistration

public final class DogsDataSourceFactory {
    private let dataSource: FakeDogsDataSource

    public init(_ container: Container) {
        dataSource = container~>
    }

    @discardableResult
    public func withDogs(count: Int) -> Self {
        dataSource.dogs = (1...count).map { index in
            DogDTO(id: index, name: "Dog \(index)", photoUrl: "woof://dogs/golden_retriever_01.jpg")
        }
        return self
    }

    @discardableResult
    public func withDogsError() -> Self {
        dataSource.getDogsError = .unavailable("Could not load dogs")
        return self
    }

    @discardableResult
    public func withoutErrors() -> Self {
        dataSource.getDogsError = nil
        dataSource.getDogProfileError = nil
        return self
    }

    @discardableResult
    public func withDogProfileError() -> Self {
        dataSource.getDogProfileError = .unavailable("Could not load profile")
        return self
    }
}
