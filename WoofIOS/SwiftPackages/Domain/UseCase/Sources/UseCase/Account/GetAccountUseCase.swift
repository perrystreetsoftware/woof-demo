import Combine
import DI
import Models
import Repositories

@Factory
public final class GetAccountUseCase {
    private let accountRepository: AccountRepository

    public func callAsFunction() -> AnyPublisher<DogProfile, AccountError> {
        accountRepository.getAccount()
    }
}
