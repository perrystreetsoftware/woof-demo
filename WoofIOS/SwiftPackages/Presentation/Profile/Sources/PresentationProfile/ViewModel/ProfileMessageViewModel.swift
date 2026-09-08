import Combine
import DI
import Models
import PresentationCommon
import UseCase
import Utils

@Factory
public final class ProfileMessageViewModel: StateProducingViewModel<ProfileMessageViewModel.State, MessageError> {
    @DIArgument private let dog: Dog
    private let sendMessageUseCase: SendMessageUseCase

    init(
        dog: Dog,
        sendMessageUseCase: SendMessageUseCase
    ) {
        self.dog = dog
        self.sendMessageUseCase = sendMessageUseCase
        super.init(initialValue: .initial)
    }

    public func onTextChange(_ text: String) {
        setState(currentState.copy(text: text))
    }

    public func onSendTap() {
        let text = currentState.text
        sendMessageUseCase(dog: dog, text: text)
            .pss_sink { [weak self] result in
                guard let self else { return }
                switch result {
                case .success: setState(currentState.copy(text: "", toast: .messageSent(name: dog.name)))
                case .failure(let error): emitError(error)
                }
            }
            .store(in: &cancellables)
    }

    public func onToastDismiss() {
        setState(currentState.copy(toast: nil))
    }
}

extension ProfileMessageViewModel {
    public struct State: Equatable {
        public let text: String
        public let toast: ProfileToastUIModel?

        public var isSendEnabled: Bool {
            !text.trimmingCharacters(in: .whitespacesAndNewlines).isEmpty
        }

        public static let initial = State(text: "", toast: nil)

        public init(text: String, toast: ProfileToastUIModel?) {
            self.text = text
            self.toast = toast
        }

        func copy(text: String? = nil, toast: ProfileToastUIModel?? = nil) -> State {
            State(text: text ?? self.text, toast: toast ?? self.toast)
        }
    }
}
