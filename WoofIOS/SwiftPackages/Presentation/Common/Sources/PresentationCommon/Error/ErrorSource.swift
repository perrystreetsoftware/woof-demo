import Combine

public struct ErrorSource {
    let errors: AnyPublisher<Swift.Error?, Never>
    let clearLastError: () -> Void

    public init<ViewModelErrorT>(_ viewModel: ErrorProducingViewModel<ViewModelErrorT>) {
        errors = viewModel.anyError
        clearLastError = { [weak viewModel] in viewModel?.clearLastError() }
    }
}
