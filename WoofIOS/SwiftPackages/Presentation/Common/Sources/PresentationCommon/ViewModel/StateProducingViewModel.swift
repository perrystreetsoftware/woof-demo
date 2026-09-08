import Combine

open class StateProducingViewModel<ViewModelStateT, ViewModelErrorT>: ErrorProducingViewModel<ViewModelErrorT>, StatefulViewModelImplementing
where ViewModelErrorT: Swift.Error {
    @Published public private(set) var state: ViewModelStateT

    public var currentState: ViewModelStateT {
        state
    }

    public init(initialValue: ViewModelStateT) {
        state = initialValue
    }

    public func setState(_ state: ViewModelStateT) {
        self.state = state
    }
}
