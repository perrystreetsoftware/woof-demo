import Combine
import DI
import Models
import PresentationCommon
import UseCase
import Utils

@Factory
public final class AccountViewModel: StateProducingViewModel<AccountViewModel.State, AccountError> {
    private let getAccountUseCase: GetAccountUseCase
    private let accountMapper: DogProfileDomainToAccountUIModelMapper

    init(
        getAccountUseCase: GetAccountUseCase,
        accountMapper: DogProfileDomainToAccountUIModelMapper
    ) {
        self.getAccountUseCase = getAccountUseCase
        self.accountMapper = accountMapper
        super.init(initialValue: .loading)
    }

    public override func onFirstAppear() {
        getAccountUseCase()
            .pss_sink { [weak self] result in
                guard let self else { return }
                switch result {
                case .success(let profile): setState(.loaded(account: accountMapper(profile: profile)))
                case .failure(let error): emitError(error)
                }
            }
            .store(in: &cancellables)
    }
}

extension AccountViewModel {
    public enum State: Equatable {
        case loading
        case loaded(account: AccountUIModel)
    }
}
