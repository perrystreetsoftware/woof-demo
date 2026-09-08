import Combine

open class ErrorProducingViewModel<ViewModelErrorT>: BaseLifecycleViewModel where ViewModelErrorT: Swift.Error {
    @Published public private(set) var error: ViewModelErrorT?

    public var anyError: AnyPublisher<Swift.Error?, Never> {
        $error.map { $0 as Swift.Error? }.eraseToAnyPublisher()
    }

    public func emitError(_ error: ViewModelErrorT) {
        self.error = error
    }

    public func clearLastError() {
        error = nil
    }
}
