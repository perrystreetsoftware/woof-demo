import Combine
import DI
import Models
import PresentationCommon
import PresentationNavigation
import UseCase
import Utils

@Factory
public final class ProfileModerationViewModel: StateProducingViewModel<ProfileModerationViewModel.State, ModerationError> {
    @DIArgument private let dog: Dog
    private let reportDogUseCase: ReportDogUseCase
    private let blockDogUseCase: BlockDogUseCase
    private let navigator: NavigatorImplementing

    init(
        dog: Dog,
        reportDogUseCase: ReportDogUseCase,
        blockDogUseCase: BlockDogUseCase,
        navigator: NavigatorImplementing
    ) {
        self.dog = dog
        self.reportDogUseCase = reportDogUseCase
        self.blockDogUseCase = blockDogUseCase
        self.navigator = navigator
        super.init(initialValue: .initial)
    }

    public func onReportTap() {
        setState(currentState.copy(dialog: .report(name: dog.name)))
    }

    public func onBlockTap() {
        setState(currentState.copy(dialog: .block(name: dog.name)))
    }

    public func onDialogDismiss() {
        setState(currentState.copy(dialog: .some(nil)))
    }

    public func onDialogConfirm() {
        guard let dialog = currentState.dialog else { return }
        setState(currentState.copy(dialog: .some(nil)))
        switch dialog {
        case .report: report()
        case .block: block()
        }
    }

    public func onToastDismiss() {
        setState(currentState.copy(toast: .some(nil)))
    }

    private func report() {
        reportDogUseCase(dog: dog)
            .pss_sink { [weak self] result in
                guard let self else { return }
                switch result {
                case .success: setState(currentState.copy(toast: .reportSent))
                case .failure(let error): emitError(error)
                }
            }
            .store(in: &cancellables)
    }

    private func block() {
        blockDogUseCase(dog: dog)
            .pss_sink { [weak self] result in
                guard let self else { return }
                switch result {
                case .success: navigator.back()
                case .failure(let error): emitError(error)
                }
            }
            .store(in: &cancellables)
    }
}

extension ProfileModerationViewModel {
    public struct State: Equatable {
        public let dialog: ProfileModerationDialogUIModel?
        public let toast: ProfileToastUIModel?

        public static let initial = State(dialog: nil, toast: nil)

        public init(dialog: ProfileModerationDialogUIModel?, toast: ProfileToastUIModel?) {
            self.dialog = dialog
            self.toast = toast
        }

        func copy(dialog: ProfileModerationDialogUIModel?? = nil, toast: ProfileToastUIModel?? = nil) -> State {
            State(dialog: dialog ?? self.dialog, toast: toast ?? self.toast)
        }
    }
}
