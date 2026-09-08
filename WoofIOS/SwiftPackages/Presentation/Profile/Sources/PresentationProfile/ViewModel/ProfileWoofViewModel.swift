import Combine
import DI
import Models
import PresentationCommon
import UseCase
import Utils

@Factory
public final class ProfileWoofViewModel: StateDerivingViewModel<ProfileWoofViewModel.State, WoofError> {
    @DIArgument private let dog: Dog
    private let sendWoofUseCase: SendWoofUseCase
    private let toast: CurrentValueSubject<ProfileToastUIModel?, Never>

    init(
        dog: Dog,
        sendWoofUseCase: SendWoofUseCase,
        hasWoofedDogUseCase: HasWoofedDogUseCase
    ) {
        let toast = CurrentValueSubject<ProfileToastUIModel?, Never>(nil)
        self.dog = dog
        self.sendWoofUseCase = sendWoofUseCase
        self.toast = toast
        super.init(
            initialValue: State(hasWoofed: false, toast: nil),
            source: Publishers.CombineLatest(
                hasWoofedDogUseCase(dog: dog),
                toast
            )
            .map { hasWoofed, toast in
                State(hasWoofed: hasWoofed, toast: toast)
            }
            .eraseToAnyPublisher()
        )
    }

    public func onWoofTap() {
        sendWoofUseCase(dog: dog)
            .pss_sink { [weak self] result in
                guard let self else { return }
                switch result {
                case .success: toast.send(.woofSent(name: dog.name))
                case .failure(let error): emitError(error)
                }
            }
            .store(in: &cancellables)
    }

    public func onToastDismiss() {
        toast.send(nil)
    }
}

extension ProfileWoofViewModel {
    public struct State: Equatable {
        public let hasWoofed: Bool
        public let toast: ProfileToastUIModel?

        public init(hasWoofed: Bool, toast: ProfileToastUIModel?) {
            self.hasWoofed = hasWoofed
            self.toast = toast
        }
    }
}
