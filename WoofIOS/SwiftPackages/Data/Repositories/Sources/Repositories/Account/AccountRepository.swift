import Combine
import DI
import DataSource
import Models

@Single
public final class AccountRepository {
    private let dataSource: AccountDataSourceImplementing
    private let profileMapper: DogProfileDTOToDomainMapper

    public func getAccount() -> AnyPublisher<DogProfile, AccountError> {
        dataSource.getAccount()
            .map { [profileMapper] in profileMapper($0) }
            .mapError { _ in AccountError.unavailable }
            .eraseToAnyPublisher()
    }
}
